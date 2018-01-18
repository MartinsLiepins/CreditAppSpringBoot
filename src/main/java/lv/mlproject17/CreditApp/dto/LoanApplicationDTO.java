package lv.mlproject17.CreditApp.dto;

import lv.mlproject17.CreditApp.threads.LoanApplicationState;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LoanApplicationDTO {
	private Long loanApplicationId;
	private Long customerId;
	private BigDecimal applicationAmount;
	private BigDecimal approvedAmount;
	private int passingTermDays;
	private LoanApplicationState state;
	private LocalDateTime date;

	public LoanApplicationDTO(){
	}

	public LoanApplicationDTO(BigDecimal amount, int passingTermDays, LocalDateTime date,
	                          Long customerId){
		this.applicationAmount = amount;
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
	public BigDecimal getApplicationAmount(){
		return applicationAmount;
	}
	public BigDecimal getApprovedAmount(){
		return approvedAmount;
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
	public void setApplicationAmount(BigDecimal applicationAmount){
		this.applicationAmount = applicationAmount;
	}
	public void setApprovedAmount(BigDecimal approvedAmount){
		this.approvedAmount = approvedAmount;
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
