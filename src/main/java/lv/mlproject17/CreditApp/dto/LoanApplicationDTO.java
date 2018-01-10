package lv.mlproject17.CreditApp.dto;

import lv.mlproject17.CreditApp.threads.LoanApplicationState;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LoanApplicationDTO {
	private Long loanApplicationId;
	private Long customerId;
	private BigDecimal amount;
	private int passingTermDays;
	private LoanApplicationState state;
	private LocalDateTime date;

	public LoanApplicationDTO(){
	}

	public LoanApplicationDTO(BigDecimal amount, int passingTermDays, LocalDateTime date,
	                          Long customerId){
		this.amount = amount;
		this.passingTermDays = passingTermDays;
		this.customerId = customerId;
		this.date = date;
	}

	public Long getLoanApplicationId(){
		return loanApplicationId;
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
	public LoanApplicationState getState(){
		return state;
	}
	public LocalDateTime getDate(){
		return date;
	}

	public void setLoanApplicationId(Long loanApplicationId){
		this.loanApplicationId = loanApplicationId;
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
	public void setState(LoanApplicationState state){
		this.state = state;
	}
	public void setDate(LocalDateTime date){
		this.date = date;
	}
}
