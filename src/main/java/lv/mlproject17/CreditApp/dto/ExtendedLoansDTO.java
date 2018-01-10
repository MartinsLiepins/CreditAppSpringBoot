package lv.mlproject17.CreditApp.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ExtendedLoansDTO {
	private BigDecimal extendedAmount;
	private int extendTermWeeks;
	private LocalDateTime extendTermDate;

	public ExtendedLoansDTO(){}

	public BigDecimal getExtendedAmount(){
		return extendedAmount;
	}
	public int getExtendTermWeeks(){
		return extendTermWeeks;
	}
	public LocalDateTime getExtendTermDate(){
		return extendTermDate;
	}

	public void setExtendedAmount(BigDecimal extendedAmount){
		this.extendedAmount = extendedAmount;
	}
	public void setExtendTermWeeks(int extendTermWeeks){
		this.extendTermWeeks = extendTermWeeks;
	}
	public void setExtendTermDate(LocalDateTime extendTermDate){
		this.extendTermDate = extendTermDate;
	}
}
