package lv.mlproject17.CreditApp.controller;

import lv.mlproject17.CreditApp.api.Response;
import lv.mlproject17.CreditApp.api.ViewLoansResponse;
import lv.mlproject17.CreditApp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/quickloan/App")
public class ApiController {
	@Autowired
	private ExtendTermService extendTermService;
	@Autowired
	private TakeLoanService takeLoanService;
	@Autowired
	private ViewLoansService viewLoansService;
	@Autowired
	private RepayLoanService repayLoanService;

	@GetMapping(path="/loan")
	@ResponseBody
	Response takeLoan(
			@RequestParam(value = "loanAmount")BigDecimal loanAmount,
			@RequestParam(value = "passingTermDays") int passingTermDays){
		return takeLoanService.takeLoan(loanAmount, passingTermDays);
	}

	@GetMapping(path="/extendLoan")
	@ResponseBody
	Response extendLoan(
			@RequestParam(value = "extendTermWeeks") int extendTermWeeks){
		return extendTermService.extendLastUserLoan(extendTermWeeks);
	}

	@RequestMapping(path="/viewLoans")
	@ResponseBody
	ViewLoansResponse viewLoans(){
				return viewLoansService.viewCustomerLoans();
			}

	@PostMapping(path="/repayLoan")
	@ResponseBody
	Response repayLoan
			(@RequestParam(value = "repayAmount") BigDecimal repayAmount){
		return repayLoanService.repayLoan(repayAmount);
	}
}






