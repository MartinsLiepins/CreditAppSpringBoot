package lv.mlproject17.CreditApp.services;

import lv.mlproject17.CreditApp.api.Error;
import lv.mlproject17.CreditApp.api.Response;
import lv.mlproject17.CreditApp.authentication.LoginUser;
import lv.mlproject17.CreditApp.database.repository.CustomerRepository;
import lv.mlproject17.CreditApp.services.validators.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private LoginValidator validator;

	public Response logIn(String email, String password){
		List<Error> validationError = validator.validate(email, password);

		if(!validationError.isEmpty()){
			return Response.failResponse(validationError);
		}

		LoginUser loginUser = new LoginUser(customerRepository.findCustomerByEmailAndPassword(
						email,password).get().getId());

		return Response.successResponse(null);
	}
}
