package lv.mlproject17.CreditApp.threads;

import lv.mlproject17.CreditApp.database.repository.LoanApplicationRepository;
import lv.mlproject17.CreditApp.database.repository.LoanRepository;
import lv.mlproject17.CreditApp.dto.LoanApplicationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RiskAnalysis {

	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

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
				getLastApplicationIdByCustomerId(dtoIn.getCustomerId());
		Long loanId = loanRepository.
				getLastLoanIdByCustomerId(dtoIn.getCustomerId());

		if(loanId != null){
			if(!loanRepository.getLoansRepayStateByLoanId(loanId)){
				dtoOut.setState(LoanApplicationState.REJECTED);
				dtoOut.setLoanApplicationId(applicationId);
				dtoOut.setCustomerId(dtoIn.getCustomerId());
				return dtoOut;
			}

			if((dtoIn.getDate().getHour() >= TIME_FROM) &&
					(dtoIn.getDate().getHour()<= TIME_TO)){
				maxAmount = MAX_LOAN_AMOUNT_IN_NIGHT;
			}else{
				maxAmount = MAX_LOAN_AMOUNT;
			}
//			interestFactor = LOAN_INTEREST_FACTOR_DAY;
		}else{
			if((dtoIn.getDate().getHour() >= TIME_FROM) &&
					(dtoIn.getDate().getHour()<= TIME_TO)){
				maxAmount = MAX_FIRST_LOAN_AMOUNT_IN_NIGHT;
			}else{
				maxAmount =  MAX_FIRST_LOAN_AMOUNT;
			}
//			interestFactor = FIRST_LOAN_INTEREST_FACTOR_DAY;
		}

		if(dtoIn.getApplicationAmount().compareTo(maxAmount) <= 0){
			dtoOut.setApplicationAmount(dtoIn.getApplicationAmount());
			dtoOut.setApprovedAmount(dtoIn.getApplicationAmount());
//			dtoOut.setApplicationAmount(dtoIn.getApplicationAmount().add
//					(dtoIn.getApplicationAmount()
//							.multiply(interestFactor)
//							.multiply(new BigDecimal(dtoIn.getPassingTermDays()))));
			dtoOut.setCustomerId(dtoIn.getCustomerId());
			dtoOut.setLoanApplicationId(applicationId);
			dtoOut.setPassingTermDays(dtoIn.getPassingTermDays());
			dtoOut.setState(LoanApplicationState.APPROVED);
		}else{
			dtoOut.setApplicationAmount(dtoIn.getApplicationAmount());
			dtoOut.setApprovedAmount(maxAmount);
//			dtoOut.setApplicationAmount(maxAmount.add
//					(maxAmount
//							.multiply(interestFactor)
//							.multiply(new BigDecimal(dtoIn.getPassingTermDays()))));
			dtoOut.setCustomerId(dtoIn.getCustomerId());
			dtoOut.setLoanApplicationId(applicationId);
			dtoOut.setPassingTermDays(dtoIn.getPassingTermDays());
			dtoOut.setState(LoanApplicationState.IN_PROCESSING);
		}
		return dtoOut;
	}
}
