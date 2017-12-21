package lv.mlproject17.CreditApp.services;

import lv.mlproject17.CreditApp.api.Response;
import lv.mlproject17.CreditApp.authentication.LoginUser;
import lv.mlproject17.CreditApp.database.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by marko on 2017.12.10..
 */
@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	private CustomerRepository customerRepository;


	@Override
	public Response logIn(String name, String password){
	Long loginId = customerRepository.findByPasswordAndName(name,password);
		if(loginId!= null){

			LoginUser loginUser = new LoginUser(loginId);

			return Response.serviceResponse("Welcome to CreditApp", true);
		}
		return Response.serviceResponse("Please register and than login", false);
	}
}
