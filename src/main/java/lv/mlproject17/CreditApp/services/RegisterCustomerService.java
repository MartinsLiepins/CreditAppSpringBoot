package lv.mlproject17.CreditApp.services;

import lv.mlproject17.CreditApp.api.Error;
import lv.mlproject17.CreditApp.api.Response;
import lv.mlproject17.CreditApp.database.model.Customer;
import lv.mlproject17.CreditApp.database.repository.CustomerRepository;
import lv.mlproject17.CreditApp.services.validators.RegisterCustomerValidator;
import lv.mlproject17.CreditApp.threads.DateAndTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by marko on 2017.12.10..
 */

@Service
public class RegisterCustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private DateAndTime dateAndTime;
	@Autowired
	private RegisterCustomerValidator validator;

	public Response registerUser(String name, String password){

		List<Error> validationError = validator.validate(name, password);

		if(!validationError.isEmpty()){
			return Response.failResponse(validationError);
		}
		customerRepository.save(customerBuilder(name, password));
		return Response.successResponse(null);
	}


	private Customer customerBuilder(String name, String password){
		Customer customer = new Customer();
		customer.setName(name);
		customer.setPassword(password);
		customer.setRegistrationDate(dateAndTime.getDateAndTimeString());
		return customer;
	}

}
