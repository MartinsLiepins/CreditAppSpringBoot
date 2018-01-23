package lv.mlproject17.CreditApp.threads;

import lv.mlproject17.CreditApp.database.model.Application;
import lv.mlproject17.CreditApp.database.model.Loan;
import lv.mlproject17.CreditApp.database.repository.ApplicationRepository;
import lv.mlproject17.CreditApp.database.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

import static lv.mlproject17.CreditApp.database.model.builders.ApplicationBuilder.createApplication;

@Component
public class RiskAnalysis {

	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	private ApplicationRepository applicationRepository;

	private final BigDecimal MAX_FIRST_LOAN_AMOUNT = new BigDecimal(400);
	private final BigDecimal MAX_LOAN_AMOUNT = new BigDecimal(1500);
	private final BigDecimal MAX_LOAN_AMOUNT_IN_NIGHT = new BigDecimal(1000);
	private final BigDecimal MAX_FIRST_LOAN_AMOUNT_IN_NIGHT = new BigDecimal(300);
	private final BigDecimal FIRST_LOAN_INTEREST_FACTOR_DAY = new BigDecimal(0);
	private final BigDecimal LOAN_INTEREST_FACTOR_DAY = new BigDecimal(0.5/100);
	private final int TIME_FROM = 0;
	private final int TIME_TO = 6;
	private final int  MAX_APPLICATION_FROM_IP_IN_DAY = 3;

	public Application applicationAnalysis(Application applicationIn){

		BigDecimal maxAmount;
		BigDecimal interestFactor;

		Optional<Loan> lastLoanOpt = loanRepository.
				findFirstByCustomerIdOrderByIdDesc(applicationIn.getCustomerId());

		if(lastLoanOpt.isPresent()){
			if(!lastLoanOpt.get().getLoanRepayState()){
				return createApplication()
						.withCustomerId(applicationIn.getCustomerId())
						.withApplicationAmount(applicationIn.getApplicationAmount())
						.withApprovedAmount(new BigDecimal(0.00))
						.withPassingTermDays(applicationIn.getPassingTermDays())
						.withApplicationStatus(ApplicationStatus.REJECTED)
						.withDate(applicationIn.getApplicationDate())
						.build();
			}
			if((applicationIn.getApplicationDate().getHour() >= TIME_FROM) &&
					(applicationIn.getApplicationDate().getHour()<= TIME_TO)){
				maxAmount = MAX_LOAN_AMOUNT_IN_NIGHT;
			}else{
				maxAmount = MAX_LOAN_AMOUNT;
			}
			interestFactor = LOAN_INTEREST_FACTOR_DAY;
		}else{
			if((applicationIn.getApplicationDate().getHour() >= TIME_FROM) &&
					(applicationIn.getApplicationDate().getHour()<= TIME_TO)){
				maxAmount = MAX_FIRST_LOAN_AMOUNT_IN_NIGHT;
			}else{
				maxAmount =  MAX_FIRST_LOAN_AMOUNT;
			}
			interestFactor = FIRST_LOAN_INTEREST_FACTOR_DAY;
		}

		if(applicationIn.getApplicationAmount().compareTo(maxAmount) <= 0){
			return createApplication()
					.withCustomerId(applicationIn.getCustomerId())
					.withApplicationAmount(applicationIn.getApplicationAmount())
					.withApprovedAmount(applicationIn.getApplicationAmount())
					.withPassingTermDays(applicationIn.getPassingTermDays())
					.withApplicationStatus(ApplicationStatus.APPROVED)
					.withInterestFactor(interestFactor)
					.withDate(applicationIn.getApplicationDate())
					.build();
		}else{
			return createApplication()
					.withCustomerId(applicationIn.getCustomerId())
					.withApplicationAmount(applicationIn.getApplicationAmount())
					.withApprovedAmount(maxAmount)
					.withPassingTermDays(applicationIn.getPassingTermDays())
					.withApplicationStatus(ApplicationStatus.IN_PROCESSING)
					.withInterestFactor(interestFactor)
					.withDate(applicationIn.getApplicationDate())
					.build();
		}
	}
}
