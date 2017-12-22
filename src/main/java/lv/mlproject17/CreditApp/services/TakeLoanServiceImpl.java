package lv.mlproject17.CreditApp.services;

import lv.mlproject17.CreditApp.api.DateAndTime;
import lv.mlproject17.CreditApp.api.Response;
import lv.mlproject17.CreditApp.authentication.LoginUser;
import lv.mlproject17.CreditApp.database.model.Loan;
import lv.mlproject17.CreditApp.database.model.LoanApplication;
import lv.mlproject17.CreditApp.database.repository.LoanApplicationRepository;
import lv.mlproject17.CreditApp.database.repository.LoanRepository;
import lv.mlproject17.CreditApp.dto.LoanApplicationDTO;
import lv.mlproject17.CreditApp.riskanalysis.LoanApplicationState;
import lv.mlproject17.CreditApp.riskanalysis.RiskAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by marko on 2017.12.10..
 */

@Service
public class TakeLoanServiceImpl implements TakeLoanService {

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	private RiskAnalysis analysis;
	@Autowired
	DateAndTime dateAndTime;


	@Override
	public Response takeLoan(BigDecimal loanAmount, int passingTerm){
		String dateTime = dateAndTime.getDateAndTimeString();

		LoanApplication loanApplication = new LoanApplication();
		loanApplication = loanApplicationBuilder(loanApplication,loanAmount,
				passingTerm, dateTime, LoginUser.logInId());
		loanApplicationRepository.save(loanApplication);

		LoanApplicationDTO dtoReceive = analysis.loanApplicationAnalysis
				(new LoanApplicationDTO(loanAmount, passingTerm, dateTime,
						LoginUser.logInId()));

		switch(dtoReceive.getState()){
			case REJECTED:
				updateLoanApplicationState(dtoReceive);
				return Response.serviceResponse("Loan application rejected", false);

			case APPROVED:
				updateLoanApplicationState(dtoReceive);
				loanRepository.save(loanBuilder(dtoReceive));
				return Response.serviceResponse("Loan application approved", true);

			case APPROVED_WITH_CONDITIONS:
				updateLoanApplicationState(dtoReceive);
				loanRepository.save(loanBuilder(dtoReceive));
				return Response.serviceResponse("Loan application approved with some conditions", true);

			default:
				return Response.serviceResponse("system error", false);
		}
	}

	private Loan loanBuilder(LoanApplicationDTO dtoReceive){
		Loan loan = new Loan();
		String dateTime = dateAndTime.getDateAndTimeString();

		loan.setCustomerId(dtoReceive.getCustomerId());
		loan.setAmount(dtoReceive.getAmount());
		loan.setIssueDate(dateTime);
		loan.setLoanExtended(false);
		loan.setPassingTerm(dtoReceive.getPassingTerm());
		return loan;
	}

	private LoanApplication loanApplicationBuilder(LoanApplication application, BigDecimal loanAmount, int passingTerm, String date, Long customerId){

		application.setApplicationAmount(loanAmount);
		application.setPassingTerm(passingTerm);
		application.setApplicationDate(date);
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
