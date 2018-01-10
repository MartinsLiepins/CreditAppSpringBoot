package lv.mlproject17.CreditApp.database.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by marko on 2017.12.08..
 */

@Entity
@Table(name = "loan")
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "loan_issue_id")
	private Long id;

	@Column(nullable = false, name = "customer_id")
	private Long customerId;

	@Column(nullable = false, name = "amount")
	private BigDecimal amount;

	@Column(nullable = false, length = 5, name = "passing_term_days")
	private int passingTermDays;

	@Column(name = "loan_extended_state")
	private boolean loanExtended;

	@Column(nullable = false, length = 80, name = "loan_issue_date")
	private String issueDate;

	@Column(name = "loan_repay_state")
	private boolean loanRepayState;

	public Loan(){}

	public Long getId(){
		return id;
	}

	public Long getCustomerId(){
	return customerId;
}
	public BigDecimal getAmount(){
		return amount;
	}
	public int getPassingTermDays(){
		return passingTermDays;
	}
	public boolean isLoanExtended(){
		return loanExtended;
	}
	public LocalDateTime getIssueDate(){
		return LocalDateTime.parse(issueDate);
	}
	public boolean getLoanRepayState(){
		return loanRepayState;
	}

	public void setId(Long id){
		this.id = id;
	}
	public void setCustomerId(Long customerId){
		this.customerId = customerId;
	}
	public void setAmount(BigDecimal amount){
		this.amount = amount;
	}
	public void setPassingTermDays(int passingTermDays){
		this.passingTermDays = passingTermDays;
	}
	public void setLoanExtended(boolean loanExtended){
		this.loanExtended = loanExtended;
	}
	public void setIssueDate(LocalDateTime takeLoanDate){
		this.issueDate = takeLoanDate.toString();
	}
	public void setLoanRepayState(boolean loanRepayState){
		this.loanRepayState = loanRepayState;
	}
}
