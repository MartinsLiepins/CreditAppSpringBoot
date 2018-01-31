package lv.mlproject17.CreditApp.services.validators;

import lv.mlproject17.CreditApp.api.Error;
import lv.mlproject17.CreditApp.database.repository.CustomerRepository;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class RegisterCustomerValidator{

	@Autowired
	private CustomerRepository customerRepository;

	public List<Error> validate(String email, String password){
		List<Error> errors = new ArrayList<>();
		validateLoginEmail(email).ifPresent(e -> errors.add(e));
		validatePassword(password).ifPresent(e -> errors.add(e));
		return errors;
	}

	private Optional<Error> validateLoginEmail(String email){
		EmailValidator validator = new EmailValidator();
		if(!validator.isValid(email, null)){
			return Optional.of(new Error("email", "Incorrect e-mail"));
		}
		else if (email == null || email.equals("")){
			return Optional.of(new Error("email", "E-mail must be not empty"));
		}
		else if (alreadyExist(email)) {
			return Optional.of(new Error("email", "E-mail already exist"));
		}else{
			return Optional.empty();
		}
	}

	private boolean alreadyExist(String email) {
		return customerRepository.findEmailByEmail(email).isPresent();
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
