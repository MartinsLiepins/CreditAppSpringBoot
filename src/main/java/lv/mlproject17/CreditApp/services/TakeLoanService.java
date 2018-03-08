package lv.mlproject17.CreditApp.services;

import lv.mlproject17.CreditApp.api.Error;
import lv.mlproject17.CreditApp.api.Response;
import lv.mlproject17.CreditApp.authentication.LoginUser;
import lv.mlproject17.CreditApp.database.model.Application;
import lv.mlproject17.CreditApp.database.model.Loan;
import lv.mlproject17.CreditApp.database.repository.ApplicationRepository;
import lv.mlproject17.CreditApp.database.repository.LoanRepository;
import lv.mlproject17.CreditApp.services.validators.ServiceErrorMessageBuilder;
import lv.mlproject17.CreditApp.services.validators.ServiceWarningMessageBuilder;
import lv.mlproject17.CreditApp.services.validators.TakeLoanValidator;
import lv.mlproject17.CreditApp.threads.ApplicationStatus;
import lv.mlproject17.CreditApp.threads.RiskAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static lv.mlproject17.CreditApp.database.model.builders.ApplicationBuilder.createApplication;
import static lv.mlproject17.CreditApp.database.model.builders.LoanBuilder.createLoan;
import static lv.mlproject17.CreditApp.threads.ApplicationStatus.APPROVED_WITH_CONDITIONS;
import static lv.mlproject17.CreditApp.threads.ApplicationStatus.IN_PROCESSING;

@Service
public class TakeLoanService {

	@Autowired
	private ApplicationRepository applicationRepository;
	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	private RiskAnalysis analysis;
	@Autowired
	private TakeLoanValidator validator;
	@Autowired
	private ServiceErrorMessageBuilder serviceErrorMessageBuilder;
	@Autowired
	private ServiceWarningMessageBuilder serviceWarningMessageBuilder;


	public Response takeLoan(BigDecimal applicationAmount, int applicationPassingTerm){

		List<Error> validationError = validator.validate(
				applicationAmount, applicationPassingTerm);

		if(!validationError.isEmpty()){
			return Response.failResponse(validationError);
		}

		Optional<Response> responseOpt = checkIfApplicationIsInProcessing(
				applicationAmount, applicationPassingTerm);
		if(responseOpt.isPresent()){
		return responseOpt.get();
		}
		return newApplication(applicationAmount, applicationPassingTerm);
	}

	@Transactional
	private Optional<Response> checkIfApplicationIsInProcessing(BigDecimal inputAmount, int inputTerm){
		Optional<Application> lastApplicationOpt =
				applicationRepository.findFirstByCustomerIdOrderByIdDesc(LoginUser.logInId());

		if(lastApplicationOpt.isPresent()){
			Application lastApplication = lastApplicationOpt.get();

			if(lastApplication.getApplicationStatus() == IN_PROCESSING){
				if(ChronoUnit.HOURS.between(lastApplication.getApplicationDate(), LocalDateTime.now()) < 12){
					if(((lastApplication.getApprovedAmount().compareTo(inputAmount)) != -1 ) && (
							lastApplication.getPassingTermDays() >= inputTerm)){

						lastApplication.setApplicationStatus(APPROVED_WITH_CONDITIONS);
						applicationRepository.save(lastApplication);
						loanRepository.save(loanCreator(lastApplication, inputAmount, inputTerm));

						return Optional.of(Response.successResponse(null));
					}
					return Optional.of(Response.failResponse
							(serviceErrorMessageBuilder.buildMessage(
									"", "Loan amount or passing term is incorrect." +
											" Approved loan amount is " + lastApplication.getApprovedAmount() +
											" EUR. Please enter correct values")));
				}
			}
		}
		return Optional.empty();
	}


	@Transactional
	private Response newApplication(BigDecimal inputAmount, int inputTerm){
		Application application =
				analysis.applicationAnalysis(applicationCreator(inputAmount, inputTerm));
		applicationRepository.save(application);
		switch(application.getApplicationStatus()){
			case REJECTED:
				return Response.failResponse
						(serviceErrorMessageBuilder.buildMessage(
								"", "Loan application rejected"));

			case APPROVED:
				loanRepository.save(loanCreator(application, inputAmount, inputTerm));
				return Response.successResponse(null);

			case IN_PROCESSING:
				return Response.successResponse
						(serviceWarningMessageBuilder.buildMessage(
								"Approved loan amount is " + application.getApprovedAmount() +
										" EUR. If you will take such amount, " +
										"please enter amount and passing term again"));

			default:
				return Response.failResponse
						(serviceErrorMessageBuilder.buildMessage("", "System error"));
		}
	}


	private Loan loanCreator(Application lastApplication, BigDecimal inputAmount, int inputTerm){

		return 	createLoan()
				.withCustomerId(lastApplication.getCustomerId())
				.withAmount(inputAmount)
				.withPassingTermDays(inputTerm)
				.withExtendState(false)
				.withDate(LocalDateTime.now())
				.withRepayState(false)
				.build();
	}

	private Application applicationCreator(BigDecimal inputAmount, int inputTerm){

		return createApplication()
				.withCustomerId(LoginUser.logInId())
				.withApplicationAmount(inputAmount)
				.withPassingTermDays(inputTerm)
				.withApplicationStatus(ApplicationStatus.IN_PROCESSING)
				.withDate(LocalDateTime.now())
				.build();
	}
}
