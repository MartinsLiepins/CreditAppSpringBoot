package lv.mlproject17.CreditApp.services;

import lv.mlproject17.CreditApp.api.Response;

import java.math.BigDecimal;

/**
 * Created by marko on 2017.12.20..
 */
public interface ReturnLoanService {
	Response returnLoan(BigDecimal loanAmount);
}
