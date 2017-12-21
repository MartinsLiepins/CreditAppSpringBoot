package lv.mlproject17.CreditApp.analisisOfRisks;

import lv.mlproject17.CreditApp.database.repository.LoanApplicationRepository;
import lv.mlproject17.CreditApp.database.repository.LoanRepository;
import lv.mlproject17.CreditApp.dto.LoanApplicationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by marko on 2017.12.12..
 */
@Service
public class RiskAnalysis {

	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	private final BigDecimal MAX_FIRST_LOAN_AMOUNT = new BigDecimal(400);
	private final BigDecimal MAX_LOAN_AMOUNT = new BigDecimal(1500);
	private final BigDecimal MAX_LOAN_AMOUNT_AFTER_MIDNIGHT = new BigDecimal(1000);
	private final BigDecimal MAX_FIRST_LOAN_AMOUNT_AFTER_MIDNIGHT = new BigDecimal(200);
	private final double FIRST_LOAN_INTEREST_FACTOR_AFTER_MIDNIGHT = 1;
	private final double FIRST_LOAN_INTEREST_FACTOR = 0;
	private final double LOAN_INTEREST_FACTOR = 1.1;
	private final int  MAX_APPLICATION_FROM_IP_IN_DAY = 3;



	public LoanApplicationDTO loanApplicationAnalysis(LoanApplicationDTO dtoIn){

		LoanApplicationDTO dtoOut = new LoanApplicationDTO();

		BigDecimal maxAmount;
		double interestFactor;

		Long applicationId = loanApplicationRepository.
				getLastUserLoanApplicationIdByCustomerId(dtoIn.getCustomerId());
		Long loanId = loanRepository.
				getLastUserLoanIdByCustomerId(dtoIn.getCustomerId());

		if(loanId != null){
			if(!loanRepository.getLoansReturnStateByLoanId(loanId)){
				dtoOut.setState(LoanApplicationState.REJECTED);
				dtoOut.setLoanApplicationId(applicationId);
				dtoOut.setCustomerId(dtoIn.getCustomerId());
				return dtoOut;
			}
			maxAmount = MAX_LOAN_AMOUNT;
			interestFactor = LOAN_INTEREST_FACTOR;
		}else{
			maxAmount =  MAX_FIRST_LOAN_AMOUNT;
			interestFactor = FIRST_LOAN_INTEREST_FACTOR;
		}

		if(dtoIn.getAmount().compareTo(maxAmount) <= 0){
			dtoOut.setAmount(dtoIn.getAmount());
			dtoOut.setCustomerId(dtoIn.getCustomerId());
			dtoOut.setLoanApplicationId(applicationId);
			dtoOut.setPassingTerm(dtoIn.getPassingTerm());
			dtoOut.setState(LoanApplicationState.APPROVED);
		}else{
			dtoOut.setAmount(maxAmount);
			dtoOut.setCustomerId(dtoIn.getCustomerId());
			dtoOut.setLoanApplicationId(applicationId);
			dtoOut.setPassingTerm(dtoIn.getPassingTerm());
			dtoOut.setState(LoanApplicationState.APPROVED_WITH_CONDITIONS);
		}
		return dtoOut;
	}



	
}
