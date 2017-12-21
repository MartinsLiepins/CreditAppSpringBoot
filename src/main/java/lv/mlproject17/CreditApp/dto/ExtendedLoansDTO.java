package lv.mlproject17.CreditApp.dto;

import java.math.BigDecimal;

/**
 * Created by marko on 2017.12.20..
 */
public class ExtendedLoansDTO {
	private BigDecimal extendedAmount;
	private int extendTermWeeks;
	private String extendTermDate;

	public ExtendedLoansDTO(){}

	public BigDecimal getExtendedAmount(){
		return extendedAmount;
	}

	public void setExtendedAmount(BigDecimal extendedAmount){
		this.extendedAmount = extendedAmount;
	}

	public int getExtendTermWeeks(){
		return extendTermWeeks;
	}

	public void setExtendTermWeeks(int extendTermWeeks){
		this.extendTermWeeks = extendTermWeeks;
	}

	public String getExtendTermDate(){
		return extendTermDate;
	}

	public void setExtendTermDate(String extendTermDate){
		this.extendTermDate = extendTermDate;
	}
}
