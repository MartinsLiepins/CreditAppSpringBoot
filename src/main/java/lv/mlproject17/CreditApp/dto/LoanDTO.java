package lv.mlproject17.CreditApp.dto;

import java.math.BigDecimal;

/**
 * Created by marko on 2017.12.19..
 */
public class LoanDTO {
	private Long id;
	private Long customerId;
	private BigDecimal amount;
	private int passingTerm;
	private boolean loanExtended;
	private String issueDate;
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
	public int getPassingTerm(){
		return passingTerm;
	}
	public boolean isLoanExtended(){
		return loanExtended;
	}
	public String getIssueDate(){
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
	public void setPassingTerm(int passingTerm){
		this.passingTerm = passingTerm;
	}
	public void setLoanExtended(boolean loanExtended){
		this.loanExtended = loanExtended;
	}
	public void setIssueDate(String issueDate){
		this.issueDate = issueDate;
	}
	public void setLoanReturnState(boolean loanReturnState){
		this.loanReturnState = loanReturnState;
	}
}


