package lv.mlproject17.CreditApp.services.validators;

import lv.mlproject17.CreditApp.api.Error;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceErrorMessageBuilder{

	public List<Error> buildMessage(String field, String errorMessage){
		Error error = new Error(field, errorMessage);
		List<Error> errors = new ArrayList<>();
		errors.add(error);
		return errors;
	}
}
