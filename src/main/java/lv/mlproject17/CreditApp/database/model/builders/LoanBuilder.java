package lv.mlproject17.CreditApp.database.model.builders;

import lv.mlproject17.CreditApp.database.model.Loan;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by marko on 2018.01.22..
 */
public class LoanBuilder {

	private Long customerId;
	private BigDecimal approvedAmount;
	private int passingTermDays;
	private BigDecimal interestFactor;
	private boolean extendState;
	private LocalDateTime date;
	private boolean repayState;

	private LoanBuilder(){}

	public static LoanBuilder createLoan(){
		return new LoanBuilder();
	}

	public Loan build(){
		Loan loan = new Loan();

		loan.setCustomerId(customerId);
		loan.setAmount(approvedAmount);
		loan.setPassingTermDays(passingTermDays);
		loan.setLoanExtended(extendState);
		loan.setIssueDate(date);
		loan.setLoanRepayState(repayState);
		return loan;
	}

	public LoanBuilder withCustomerId(Long customerId){
		this.customerId = customerId;
		return this;
	}

	public LoanBuilder withAmount(BigDecimal approvedAmount){
		this.approvedAmount = approvedAmount;
		return this;
	}

	public LoanBuilder withPassingTermDays(int passingTermDays){
		this.passingTermDays = passingTermDays;
		return this;
	}

	public LoanBuilder withExtendState(boolean extendState){
		this.extendState = extendState;
		return this;
	}

	public LoanBuilder withDate(LocalDateTime date){
		this.date = date;
		return this;
	}

	public LoanBuilder withRepayState(boolean repayState){
		this.repayState = repayState;
		return this;
	}
}
