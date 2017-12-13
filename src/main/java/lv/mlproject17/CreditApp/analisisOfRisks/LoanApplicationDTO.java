package lv.mlproject17.CreditApp.analisisOfRisks;

import java.math.BigDecimal;

/**
 * Created by marko on 2017.12.13..
 */
public class LoanApplicationDTO {

	private BigDecimal approvedAmount;
	private int approvedPassingTerm;
	private String applicationStatus;

	public LoanApplicationDTO(BigDecimal approvedAmount,
	                          int approvedPassingTerm,
	                          String applicationStatus){

		this.approvedAmount = approvedAmount;
		this.approvedPassingTerm = approvedPassingTerm;
		this.applicationStatus = applicationStatus;
	}
}
