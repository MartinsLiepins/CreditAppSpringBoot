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
import lv.mlproject17.CreditApp.threads.DateAndTime;
import lv.mlproject17.CreditApp.threads.LoanApplicationState;
import lv.mlproject17.CreditApp.threads.RiskAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
	@Autowired
	DateAndTime dateAndTime;

	public Response takeLoan(BigDecimal loanAmount, int passingTerm){

		List<Error> validationError = validator.validate(loanAmount, passingTerm);

		if(!validationError.isEmpty()){
			return Response.failResponse(validationError);
		}

		LoanApplication loanApplication = new LoanApplication();

		loanApplication = loanApplicationBuilder(loanApplication,loanAmount,
				passingTerm, LoginUser.logInId());

		loanApplicationRepository.save(loanApplication);

		LoanApplicationDTO dtoReceive = analysis.loanApplicationAnalysis
				(new LoanApplicationDTO(loanAmount, passingTerm,
						LocalDateTime.now(),LoginUser.logInId()));

		updateLoanApplicationState(dtoReceive);

		switch(dtoReceive.getState()){
			case REJECTED:
				return Response.failResponse
						(serviceErrorMessageBuilder.buildMessage
								("", "Loan application rejected"));

			case APPROVED:
				loanRepository.save(loanBuilder(dtoReceive));
				return Response.successResponse(null);

			case APPROVED_WITH_CONDITIONS:
				loanRepository.save(loanBuilder(dtoReceive));
				return Response.successResponse
						(serviceWarningMessageBuilder.buildMessage
								("Approved loan amount is " +dtoReceive.getAmount()+
										" EUR"));

			default:
				return Response.failResponse
						(serviceErrorMessageBuilder.buildMessage("", "System error"));
		}
	}

	private Loan loanBuilder(LoanApplicationDTO dtoReceive){
		Loan loan = new Loan();

		loan.setCustomerId(dtoReceive.getCustomerId());
		loan.setAmount(dtoReceive.getAmount());
		loan.setIssueDate(LocalDateTime.now());
		loan.setLoanExtended(false);
		loan.setLoanRepayState(false);
		loan.setPassingTermDays(dtoReceive.getPassingTermDays());
		return loan;
	}

	private LoanApplication loanApplicationBuilder(LoanApplication application,
	                                               BigDecimal loanAmount,
	                                               int passingTerm, Long customerId){

		application.setApplicationAmount(loanAmount);
		application.setPassingTermDays(passingTerm);
		application.setApplicationDate(LocalDateTime.now());
		application.setCustomerId(customerId);
		LoanApplicationState ss = (LoanApplicationState.IN_PROCESSING);
		application.setApplicationStatus(ss.toString());
		return application;
	}

	 private void updateLoanApplicationState(LoanApplicationDTO dto){
		loanApplicationRepository.updateApplicationState
				((dto.getState()).toString(), dto.getLoanApplicationId());
	}
}
