package lv.mlproject17.CreditApp.controller;

import lv.mlproject17.CreditApp.api.Response;
import lv.mlproject17.CreditApp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quickloan")
public class CredentialsController {
	@Autowired
	private RegisterCustomerService registerCustomerService;

	@Autowired
	private LoginService loginService;

	@PostMapping(path="/register")
	@ResponseBody
	public Response registerNewCustomer(
			@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password){
		return registerCustomerService.registerUser(email, password);
	}

	@PostMapping(path="/login")
	@ResponseBody
	Response logIn(
			@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password){
		return loginService.logIn(email, password);
	}
}

