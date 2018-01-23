package lv.mlproject17.CreditApp.services.validators;

import lv.mlproject17.CreditApp.api.Error;
import lv.mlproject17.CreditApp.database.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LoginValidator {

	@Autowired
	private CustomerRepository customerRepository;

	public List<Error> validate(String name, String password){
		List<Error> errors = new ArrayList<>();
		validateLogin(name).ifPresent(e -> errors.add(e));
		validatePassword(password).ifPresent(e -> errors.add(e));
		validateCustomer(name, password).ifPresent(e -> errors.add(e));
		return errors;
	}

	private Optional<Error> validateLogin(String name){
		if (name == null || name.equals("")) {
			return Optional.of(new Error("login", "Login must by not empty"));
		}else{
			return Optional.empty();
		}
	}

	private Optional<Error> validatePassword(String password){
		if (password == null || password.equals("")){
			return Optional.of(new Error("password", "Password must by not empty"));
		}else if (password.length() < 4){
			return Optional.of(new Error("password", "Password must by not shorter than 4 symbols"));
		}else{
			return Optional.empty();
		}
	}

	private Optional<Error> validateCustomer(String email, String password){
		if(notExist(email, password)){
			return Optional.of(new Error("name, password", "Incorrect name or password"));
		}else{
			return Optional.empty();
		}
	}

	private boolean notExist(String email, String password){
		return (!customerRepository.findCustomerByEmailAndPassword(email,password).isPresent());
	}
}
