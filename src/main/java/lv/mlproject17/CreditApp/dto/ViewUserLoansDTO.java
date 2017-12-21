package lv.mlproject17.CreditApp.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by marko on 2017.12.20..
 */
public class ViewUserLoansDTO {


	private String name;
	private BigDecimal amount;
	private int passingTerm;
	private String loanIssueDate;
	private List<ExtendedLoansDTO> extendedLoans;

	public ViewUserLoansDTO(){}

	public void setName(String name){
		this.name = name;
	}
	public void setAmount(BigDecimal amount){
		this.amount = amount;
	}
	public void setPassingTerm(int passingTerm){
		this.passingTerm = passingTerm;
	}
	public void setLoanIssueDate(String loanIssueDate){
		this.loanIssueDate = loanIssueDate;
	}
	public void setExtendedLoans(List<ExtendedLoansDTO> loanIsExtended){
		this.extendedLoans = loanIsExtended;
	}

	public String getName(){
		return name;
	}
	public BigDecimal getAmount(){
		return amount;
	}
	public int getPassingTerm(){
		return passingTerm;
	}
	public String getLoanIssueDate(){
		return loanIssueDate;
	}
	public List<ExtendedLoansDTO> getExtendedLoans(){
		return extendedLoans;
	}
}
