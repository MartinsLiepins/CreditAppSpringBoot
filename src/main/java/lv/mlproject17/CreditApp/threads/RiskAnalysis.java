package lv.mlproject17.CreditApp.threads;

import lv.mlproject17.CreditApp.database.repository.LoanApplicationRepository;
import lv.mlproject17.CreditApp.database.repository.LoanRepository;
import lv.mlproject17.CreditApp.dto.LoanApplicationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by marko on 2017.12.12..
 */
//@Service
@Component
public class RiskAnalysis {

	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	@Autowired
	DateAndTime dateAndTime;

	private final BigDecimal MAX_FIRST_LOAN_AMOUNT = new BigDecimal(400);
	private final BigDecimal MAX_LOAN_AMOUNT = new BigDecimal(1500);
	private final BigDecimal MAX_LOAN_AMOUNT_IN_NIGHT = new BigDecimal(1000);
	private final BigDecimal MAX_FIRST_LOAN_AMOUNT_IN_NIGHT = new BigDecimal(300);
	private final BigDecimal FIRST_LOAN_INTEREST_FACTOR_DAY = new BigDecimal(0);
	private final BigDecimal LOAN_INTEREST_FACTOR_DAY = new BigDecimal(0.5/100);
	private final int TIME_FROM = 0;
	private final int TIME_TO = 6;
	private final int  MAX_APPLICATION_FROM_IP_IN_DAY = 3;

	public LoanApplicationDTO loanApplicationAnalysis(LoanApplicationDTO dtoIn){

		LoanApplicationDTO dtoOut = new LoanApplicationDTO();

		BigDecimal maxAmount;
		BigDecimal interestFactor;

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

			if((dateAndTime.getHourFromString(dtoIn.getDate()) >= TIME_FROM) &&
					(dateAndTime.getHourFromString(dtoIn.getDate())<= TIME_TO)){
				maxAmount = MAX_LOAN_AMOUNT_IN_NIGHT;
			}else{
				maxAmount = MAX_LOAN_AMOUNT;
			}
			interestFactor = LOAN_INTEREST_FACTOR_DAY;
		}else{
			if((dateAndTime.getHourFromString(dtoIn.getDate()) >= TIME_FROM) &&
					(dateAndTime.getHourFromString(dtoIn.getDate())<= TIME_TO)){
				maxAmount = MAX_FIRST_LOAN_AMOUNT_IN_NIGHT;
			}else{
				maxAmount =  MAX_FIRST_LOAN_AMOUNT;
			}
			interestFactor = FIRST_LOAN_INTEREST_FACTOR_DAY;
		}

		if(dtoIn.getAmount().compareTo(maxAmount) <= 0){
			dtoOut.setAmount(dtoIn.getAmount().add
					(dtoIn.getAmount()
							.multiply(interestFactor)
							.multiply(new BigDecimal(dtoIn.getPassingTerm()))));
			dtoOut.setCustomerId(dtoIn.getCustomerId());
			dtoOut.setLoanApplicationId(applicationId);
			dtoOut.setPassingTerm(dtoIn.getPassingTerm());
			dtoOut.setState(LoanApplicationState.APPROVED);
		}else{
			dtoOut.setAmount(maxAmount.add
					(maxAmount
							.multiply(interestFactor)
							.multiply(new BigDecimal(dtoIn.getPassingTerm()))));
			dtoOut.setCustomerId(dtoIn.getCustomerId());
			dtoOut.setLoanApplicationId(applicationId);
			dtoOut.setPassingTerm(dtoIn.getPassingTerm());
			dtoOut.setState(LoanApplicationState.APPROVED_WITH_CONDITIONS);
		}
		return dtoOut;
	}
}
