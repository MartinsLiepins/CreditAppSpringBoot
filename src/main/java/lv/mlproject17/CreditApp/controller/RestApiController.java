package lv.mlproject17.CreditApp.controller;

import lv.mlproject17.CreditApp.api.Response;
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
	private ReturnLoanService returnLoanService;


	@GetMapping(path="/register")
	@ResponseBody
	Response registerNewCustomer
			(@RequestParam(value = "name") String name,
			 @RequestParam(value ="password") String password){
		return registerCustomerService.registerUser(name, password);
	}

	@GetMapping(path="/login")
	@ResponseBody
	Response logIn
			(@RequestParam(value = "name") String name,
			 @RequestParam(value = "password") String password){
		return loginService.logIn(name, password);
	}

	@GetMapping(path="/login/loan")
	@ResponseBody
	Response takeLoan
			(@RequestParam(value = "loanAmount")BigDecimal loanAmount,
			 @RequestParam(value = "passingTerm") int passingTerm){
		return takeLoanService.takeLoan(loanAmount, passingTerm);
	}

	@GetMapping(path="/login/extendLoan")
	@ResponseBody
	Response extendLoan
			(@RequestParam(value = "extendTermWeeks") int extendTermWeeks){
		return extendTermService.extendLastUserLoan(extendTermWeeks);
	}

	@RequestMapping(path="/login/viewLoans")
	@ResponseBody
	Response viewLoans(){
				return viewLoansService.viewCustomerLoans();
			}

	@GetMapping(path="/login/returnLoan")
	@ResponseBody
	Response returnLoan
			(@RequestParam(value = "returnAmount") BigDecimal returnAmount){
		return returnLoanService.returnLoan(returnAmount);
	}
}






