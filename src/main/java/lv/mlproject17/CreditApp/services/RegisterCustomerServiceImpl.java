package lv.mlproject17.CreditApp.services;

import lv.mlproject17.CreditApp.api.Response;
import lv.mlproject17.CreditApp.database.model.Customer;
import lv.mlproject17.CreditApp.database.repository.CustomerRepository;
import lv.mlproject17.CreditApp.database.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by marko on 2017.12.10..
 */

@Service
public class RegisterCustomerServiceImpl implements RegisterCustomerService{
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private LoanRepository loanRepository;

	@Override
	public Response registerUser(String name, String password){

		if(customerRepository.findByPasswordAndName(name,password) == null){
			Customer customer = new Customer(name, password,
								LocalDateTime.now().toString());
			customerRepository.save(customer);
			return Response.serviceResponse("You are registered", true);
		}
		return Response.serviceResponse("Registration error", false);
	}
}
