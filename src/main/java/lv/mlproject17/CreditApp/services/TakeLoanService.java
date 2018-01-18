package lv.mlproject17.CreditApp.services;

import lv.mlproject17.CreditApp.api.Error;
import lv.mlproject17.CreditApp.api.Response;
import lv.mlproject17.CreditApp.authentication.LoginUser;
import lv.mlproject17.CreditApp.database.model.Loan;
import lv.mlproject17.CreditApp.database.model.LoanApplication;
import lv.mlproject17.CreditApp.database.repository.LoanApplicationRepository;
import lv.mlproject17.CreditApp.database.repository.LoanRepository;
import lv.mlproject17.CreditApp.dto.LoanApplicationDTO;
import lv.mlproject17.CreditApp.services.validators.ServiceErrorMessageBuilder;
import lv.mlproject17.CreditApp.services.validators.ServiceWarningMessageBuilder;
import lv.mlproject17.CreditApp.services.validators.TakeLoanValidator;
import lv.mlproject17.CreditApp.threads.LoanApplicationState;
import lv.mlproject17.CreditApp.threads.RiskAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static lv.mlproject17.CreditApp.threads.LoanApplicationState.APPROVED_WITH_CONDITIONS;
import static lv.mlproject17.CreditApp.threads.LoanApplicationState.IN_PROCESSING;

@Service
public class TakeLoanService {

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	private RiskAnalysis analysis;
	@Autowired
	private TakeLoanValidator validator;
	@Autowired
	private ServiceErrorMessageBuilder serviceErrorMessageBuilder;
	@Autowired
	ServiceWarningMessageBuilder serviceWarningMessageBuilder;


	public Response takeLoan(BigDecimal applicationAmount, int applicationPassingTerm){

		List<Error> validationError = validator.validate(applicationAmount, applicationPassingTerm);

		if(!validationError.isEmpty()){
			return Response.failResponse(validationError);
		}

		Optional<LoanApplication> lastApplicationOpt =
				loanApplicationRepository.getLastApplicationByCustomerId(LoginUser.logInId());

		if(lastApplicationOpt.isPresent()){
			LoanApplication lastApplication = lastApplicationOpt.get();
			LoanApplicationDTO lastApplicationDTO = loanApplicationDTOBuilder(lastApplication);

			if(lastApplicationDTO.getState() == IN_PROCESSING){
				if(ChronoUnit.HOURS.between(lastApplicationDTO.getDate(), LocalDateTime.now()) < 12){
					if((lastApplicationDTO.getApplicationAmount().compareTo(applicationAmount) == (1 | 0)) && (
							lastApplicationDTO.getPassingTermDays() >= applicationPassingTerm)){

						lastApplicationDTO.setState(APPROVED_WITH_CONDITIONS);
						updateLoanApplicationState(lastApplicationDTO);
						loanRepository.save(loanBuilder(lastApplicationDTO));
						return Response.successResponse(null);
					}
					return Response.failResponse
							(serviceErrorMessageBuilder.buildMessage(
									"", "Loan amount or passing term is incorrect." +
											"Approved loan amount is " + lastApplicationDTO.getApprovedAmount() +
											" EUR. Please enter correct values"));
				}
			}
		}

		LoanApplication newApplication = loanApplicationBuilder(applicationAmount,
					applicationPassingTerm, LoginUser.logInId(), IN_PROCESSING);

			loanApplicationRepository.save(newApplication);

			LoanApplicationDTO dtoReceive = analysis.loanApplicationAnalysis(
					new LoanApplicationDTO(applicationAmount, applicationPassingTerm,
							LocalDateTime.now(), LoginUser.logInId()));

			updateLoanApplicationState(dtoReceive);

			switch(dtoReceive.getState()){
				case REJECTED:
					return Response.failResponse
							(serviceErrorMessageBuilder.buildMessage(
									"", "Loan application rejected"));

				case APPROVED:
					loanRepository.save(loanBuilder(dtoReceive));
					return Response.successResponse(null);

				case IN_PROCESSING:
					return Response.successResponse
							(serviceWarningMessageBuilder.buildMessage(
									"Approved loan amount is " + dtoReceive.getApplicationAmount() +
											" EUR. If you will take such amount, " +
											"please enter amount and passing term again"));

				default:
					return Response.failResponse
							(serviceErrorMessageBuilder.buildMessage("", "System error"));
			}
	}

	private LoanApplicationDTO loanApplicationDTOBuilder(LoanApplication loanApplication){
		LoanApplicationDTO dto = new LoanApplicationDTO();
		dto.setState(LoanApplicationState.valueOf(
				loanApplication.getApplicationStatus()));
		dto.setDate(loanApplication.getApplicationDate());
		return dto;
	}

	private Loan loanBuilder(LoanApplicationDTO dtoReceive){
		Loan loan = new Loan();

		loan.setCustomerId(dtoReceive.getCustomerId());
		loan.setAmount(dtoReceive.getApprovedAmount());
		loan.setIssueDate(LocalDateTime.now());
		loan.setLoanExtended(false);
		loan.setLoanRepayState(false);
		loan.setPassingTermDays(dtoReceive.getPassingTermDays());
		return loan;
	}

	private LoanApplication loanApplicationBuilder(BigDecimal applicationAmount,
	                                               int passingTerm, Long customerId,
	                                               LoanApplicationState state){
		LoanApplication application = new LoanApplication();
		application.setApplicationAmount(applicationAmount);
		application.setApprovedAmount(new BigDecimal(0));
		application.setPassingTermDays(passingTerm);
		application.setApplicationDate(LocalDateTime.now());
		application.setCustomerId(customerId);
		application.setApplicationStatus(state.toString());
		return application;
	}

	 private void updateLoanApplicationState(LoanApplicationDTO dto){


		loanApplicationRepository.updateApplicationState
				((dto.getState()).toString(), dto.getLoanApplicationId());



//		loanApplicationRepository.updateApplicationState
//				((loanApplicationBuilder(dto));
	}
}
