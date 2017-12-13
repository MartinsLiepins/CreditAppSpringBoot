package lv.mlproject17.CreditApp.controller;

import lv.mlproject17.CreditApp.api.Response;
import lv.mlproject17.CreditApp.services.LoginService;
import lv.mlproject17.CreditApp.services.RegisterCustomerService;
import lv.mlproject17.CreditApp.services.TakeLoanService;
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
	private Response response;
//	@Autowired
//	private ExtendTermService extendTermService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private TakeLoanService takeLoanService;
//	@Autowired
//	private ViewUserLoansService viewUserLoansService;

	@GetMapping(path="/register")
	public @ResponseBody
	Response registerNewCustomer
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

	@GetMapping(path="/loan")
	public @ResponseBody Response takeLoan
			(@RequestParam(value = "loanAmount")BigDecimal loanAmount,
			 @RequestParam(value = "passingTerm") int passingTerm){

		response = takeLoanService.takeLoan(loanAmount, passingTerm);

		return response;
	}

//	@PostMapping(path="logout")
//	public @ResponseBody String logout
//			(@RequestParam(value = "loanAmount")BigDecimal loanAmount,
//			 @RequestParam(value = "passingTerm") int passingTerm){

//			return "user logged out";
//		}
}






