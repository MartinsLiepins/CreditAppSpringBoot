package lv.mlproject17.CreditApp.dto;

import lv.mlproject17.CreditApp.riskanalysis.LoanApplicationState;

import java.math.BigDecimal;

/**
 * Created by marko on 2017.12.13..
 */
public class LoanApplicationDTO {

	private BigDecimal amount;
	private int passingTerm;
	private LoanApplicationState state;
	private String date;
	private Long customerId;
	private Long loanApplicationId;

	public LoanApplicationDTO(){
	}

//	public LoanApplicationDTO(LoanApplicationState state){
//		this.state = state;
//	}

	public LoanApplicationDTO(BigDecimal amount, int passingTerm, String date,
	                          Long customerId){
		this.amount = amount;
		this.passingTerm = passingTerm;
		this.customerId = customerId;
		this.date = date;
	}

	public BigDecimal getAmount(){
		return amount;
	}

	public void setAmount(BigDecimal amount){
		this.amount = amount;
	}

	public int getPassingTerm(){
		return passingTerm;
	}

	public void setPassingTerm(int passingTerm){
		this.passingTerm = passingTerm;
	}

	public LoanApplicationState getState(){
		return state;
	}

	public void setState(LoanApplicationState state){
		this.state = state;
	}

	public String getDate(){
		return date;
	}

	public void setDate(String date){
		this.date = date;
	}

	public Long getCustomerId(){
		return customerId;
	}

	public void setCustomerId(Long customerId){
		this.customerId = customerId;
	}

	public Long getLoanApplicationId(){
		return loanApplicationId;
	}

	public void setLoanApplicationId(Long loanApplicationId){
		this.loanApplicationId = loanApplicationId;
	}
}
