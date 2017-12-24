package lv.mlproject17.CreditApp.services.validators;

import lv.mlproject17.CreditApp.api.Error;
import lv.mlproject17.CreditApp.database.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class RegisterCustomerValidator{

	@Autowired
	private CustomerRepository customerRepository;

	public List<Error> validate(String name, String password){
		List<Error> errors = new ArrayList<>();
		validateLogin(name).ifPresent(e -> errors.add(e));
		validatePassword(password).ifPresent(e -> errors.add(e));
		return errors;
	}

	private Optional<Error> validateLogin(String name){
		if (name == null || name.equals("")) {
			return Optional.of(new Error("login", "Login must be not empty"));
		}else if (alreadyExist(name)) {
			return Optional.of(new Error("login", "Login already exist"));
		}else{
			return Optional.empty();
		}
	}

	private boolean alreadyExist(String loginName) {
		return customerRepository.findNameByLoginName(loginName).isPresent();
	}

	private Optional<Error> validatePassword(String password){
		if (password == null || password.equals("")){
			return Optional.of(new Error("password", "Password must be not empty"));
		}else if (password.length() < 4){
			return Optional.of(new Error("password", "Password must be not shorter than 4 symbols"));
		}else{
			return Optional.empty();
		}
	}
}
