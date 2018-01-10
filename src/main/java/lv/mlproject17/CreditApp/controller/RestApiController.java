package lv.mlproject17.CreditApp.controller;

import lv.mlproject17.CreditApp.api.Response;
import lv.mlproject17.CreditApp.api.ViewLoansResponse;
import lv.mlproject17.CreditApp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Created by marko on 2017.12.08..
 */

@RestController
@RequestMapping("/demo")
public class RestApiController {
	@Autowired
	private RegisterCustomerService registerCustomerService;
	@Autowired
	private ExtendTermService extendTermService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private TakeLoanService takeLoanService;
	@Autowired
	private ViewLoansService viewLoansService;
	@Autowired
	private RepayLoanService repayLoanService;


	@GetMapping(path="/register")
	@ResponseBody
	Response registerNewCustomer
			(@RequestParam(value = "email") String email,
			 @RequestParam(value ="password") String password){
		return registerCustomerService.registerUser(email, password);
	}
//	@PostMapping(path="/register")
//	@ResponseBody
//	Response registerNewCustomer(
//			@RequestBody String email, String password){
//		return registerCustomerService.registerUser(email, password);
//	}

	@GetMapping(path="/login")
	@ResponseBody
	Response logIn
			(@RequestParam(value = "email") String email,
			 @RequestParam(value = "password") String password){
		return loginService.logIn(email, password);
	}

	@GetMapping(path="/login/loan")
	@ResponseBody
	Response takeLoan(
			@RequestParam(value = "loanAmount")BigDecimal loanAmount,
			@RequestParam(value = "passingTermDays") int passingTermDays){
		return takeLoanService.takeLoan(loanAmount, passingTermDays);
	}

	@GetMapping(path="/login/extendLoan")
	@ResponseBody
	Response extendLoan(
			@RequestParam(value = "extendTermWeeks") int extendTermWeeks){
		return extendTermService.extendLastUserLoan(extendTermWeeks);
	}

	@RequestMapping(path="/login/viewLoans")
	@ResponseBody
	ViewLoansResponse viewLoans(){
				return viewLoansService.viewCustomerLoans();
			}

	@GetMapping(path="/login/repayLoan")
	@ResponseBody
	Response repayLoan
			(@RequestParam(value = "repayAmount") BigDecimal repayAmount){
		return repayLoanService.returnLoan(repayAmount);
	}
}






