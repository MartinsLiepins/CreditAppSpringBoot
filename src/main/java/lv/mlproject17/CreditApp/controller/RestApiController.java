package lv.mlproject17.CreditApp.controller;

import lv.mlproject17.CreditApp.api.Response;
import lv.mlproject17.CreditApp.dto.ViewUserLoansDTO;
import lv.mlproject17.CreditApp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

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
	private ViewUserLoansService viewUserLoansService;
	@Autowired
	private ReturnLoanService returnLoanService;

	private Response response;

	@GetMapping(path="/register")
	public @ResponseBody Response registerNewCustomer
			(@RequestParam(value = "name") String name,
			 @RequestParam(value ="password") String password){

		response = registerCustomerService.registerUser(name, password);
		return response;
	}

	@GetMapping(path="/login")
	public @ResponseBody Response logIn
			(@RequestParam(value = "name") String name,
			 @RequestParam(value = "password") String password){

				response = loginService.logIn(name, password);
			return response;
	}

	@GetMapping(path="/login/loan")
	public @ResponseBody Response takeLoan
			(@RequestParam(value = "loanAmount")BigDecimal loanAmount,
			 @RequestParam(value = "passingTerm") int passingTerm){

		response = takeLoanService.takeLoan(loanAmount, passingTerm);
		return response;
	}

	@GetMapping(path="/login/extendLoan")
	public @ResponseBody Response extendLoan
			(@RequestParam(value = "extendTermWeeks") int extendTermWeeks){

		response = extendTermService.extendLastUserLoan(extendTermWeeks);
		return response;
	}

	@GetMapping(path="/login/viewLoans")
	public @ResponseBody List<ViewUserLoansDTO> viewLoans
			(@RequestParam(value = "customerId") Long customerId){

		List<ViewUserLoansDTO> viewLoans = viewUserLoansService.viewCustomerLoans(customerId);
		return viewLoans;
	}

	@GetMapping(path="/login/returnLoan")
	public @ResponseBody Response returnLoan
			(@RequestParam(value = "returnAmount") BigDecimal returnAmount){

		response = returnLoanService.returnLoan(returnAmount);
		return response;
	}
}






