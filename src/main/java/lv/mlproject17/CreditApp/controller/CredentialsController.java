package lv.mlproject17.CreditApp.controller;

import lv.mlproject17.CreditApp.api.Response;
import lv.mlproject17.CreditApp.dto.CredentialsDto;
import lv.mlproject17.CreditApp.services.LoginService;
import lv.mlproject17.CreditApp.services.RegisterCustomerService;
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
	public Response registerNewCustomer(
			@RequestBody CredentialsDto credentialsDto){
		return registerCustomerService.registerUser(
				credentialsDto.getEmail(), credentialsDto.getPassword());
	}

	@PostMapping(path="/login")
	@ResponseBody
	public Response logIn(
			@RequestBody CredentialsDto credentialsDto){
		return loginService.logIn(credentialsDto.getEmail(), credentialsDto.getPassword());
	}
}

