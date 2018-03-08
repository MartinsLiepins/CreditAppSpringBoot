package lv.mlproject17.CreditApp.dto;

import java.math.BigDecimal;

/**
 * Created by martins.
 */
public class RepayLoanDto {
	private BigDecimal repayAmount;

	public RepayLoanDto(){}

	public RepayLoanDto(BigDecimal repayAmount){
		this.repayAmount = repayAmount;
	}

	public BigDecimal getRepayAmount(){
		return repayAmount;
	}

	public void setRepayAmount(BigDecimal repayAmount){
		this.repayAmount = repayAmount;
	}
}
