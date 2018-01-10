package lv.mlproject17.CreditApp.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by marko on 2017.12.19..
 */
public class LoanDTO {
	private Long id;
	private Long customerId;
	private BigDecimal amount;
	private int passingTermDays;
	private boolean loanExtended;
	private LocalDateTime issueDate;
	private boolean loanReturnState;

	public LoanDTO(){}

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
		return issueDate;
	}
	public boolean getLoanReturnState(){
		return loanReturnState;
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
	public void setIssueDate(LocalDateTime issueDate){
		this.issueDate = issueDate;
	}
	public void setLoanReturnState(boolean loanReturnState){
		this.loanReturnState = loanReturnState;
	}
}


