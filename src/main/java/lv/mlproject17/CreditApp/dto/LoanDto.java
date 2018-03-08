package lv.mlproject17.CreditApp.dto;

import java.math.BigDecimal;

/**
 * Created by martins.
 */
public class LoanDto {
	private BigDecimal amount;
	private int term;

	public LoanDto(){}

	public LoanDto(BigDecimal loanAmount, int term){
		this.amount = loanAmount;
		this.term = term;
	}

	public LoanDto(BigDecimal loanAmount){
		this.amount = loanAmount;
	}

	public LoanDto(int term){
		this.term = term;
	}

	public BigDecimal getAmount(){
		return amount;
	}
	public int getTerm(){
		return term;
	}
	public void setAmount(BigDecimal amount){
		this.amount = amount;
	}
	public void setTerm(int term){
		this.term = term;
	}
}
