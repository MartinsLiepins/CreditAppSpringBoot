package lv.mlproject17.CreditApp.analisisOfRisks;

import java.math.BigDecimal;

/**
 * Created by marko on 2017.12.12..
 */
public class RiskAnalysis {
	 private final BigDecimal MAX_FIRST_LOAN_AMOUNT = new BigDecimal(400);
	 private final BigDecimal MAX_LOAN_AMOUNT = new BigDecimal(1500);
	 private final BigDecimal MAX_LOAN_AMOUNT_AFTER_MIDNIGHT = new BigDecimal(1000);
	 private final BigDecimal MAX_FIRST_LOAN_AMOUNT_AFTER_MIDNIGHT = new BigDecimal(200);
	 private final double FIRST_LOAN_INTEREST_FACTOR_AFTER_MIDNIGHT = 1;
	 private final double FIRST_LOAN_INTEREST_FACTOR = 0;
	 private final double LOAN_INTEREST_FACTOR = 1.1;
	 private final int  MAX_APPLICATION_FROM_IP_IN_DAY = 3;

//	 String loanApplication;
//
//	 public static boolean loanApplicationAnalysis(){
//
//
//	 	return new LoanApplicationDTO(acceptedTerm)
//	 }



	
}
