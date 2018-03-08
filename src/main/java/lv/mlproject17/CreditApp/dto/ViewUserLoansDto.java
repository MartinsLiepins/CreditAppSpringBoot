package lv.mlproject17.CreditApp.dto;

import lv.mlproject17.CreditApp.database.model.ExtendedLoans;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by marko on 2017.12.20..
 */
public class ViewUserLoansDto {


	private String name;
	private BigDecimal amount;
	private int passingTermDays;
	private LocalDateTime loanIssueDate;
	private boolean loanReturnState;
	private List<ExtendedLoans> extendedLoans;

	public ViewUserLoansDto(){}

	public void setName(String name){
		this.name = name;
	}
	public void setAmount(BigDecimal amount){
		this.amount = amount;
	}
	public void setPassingTermDays(int passingTermDays){
		this.passingTermDays = passingTermDays;
	}
	public void setLoanIssueDate(LocalDateTime loanIssueDate){
		this.loanIssueDate = loanIssueDate;
	}
	public void setLoanReturnState(boolean loanReturnState){
		this.loanReturnState = loanReturnState;
	}
	public void setExtendedLoans(List<ExtendedLoans> loanIsExtended){
		this.extendedLoans = loanIsExtended;
	}

	public String getName(){
		return name;
	}
	public BigDecimal getAmount(){
		return amount;
	}
	public int getPassingTermDays(){
		return passingTermDays;
	}
	public LocalDateTime getLoanIssueDate(){
		return loanIssueDate;
	}
	public boolean getLoanReturnState(){
		return loanReturnState;
	}
	public List<ExtendedLoans> getExtendedLoans(){
		return extendedLoans;
	}
}
