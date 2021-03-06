package lv.mlproject17.CreditApp.services.validators;

import lv.mlproject17.CreditApp.api.Error;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class TakeLoanValidator {

	public List<Error> validate(BigDecimal loanAmount, int passingTerm){
		List<Error> errors = new ArrayList<>();
		validateAmount(loanAmount).ifPresent(e -> errors.add(e));
		validateTerm(passingTerm).ifPresent(e -> errors.add(e));
		return errors;
	}

	private Optional<Error> validateAmount(BigDecimal loanAmount){
		if (loanAmount == null || loanAmount.equals("")) {
			return Optional.of(new Error("Loan amount field", "must not be empty"));
		}else if (loanAmount.equals(BigDecimal.ZERO)) {
			return Optional.of(new Error("Loan amount field", "must not be zero value"));
		}else if (loanAmount.compareTo(BigDecimal.ZERO)<0) {
			return Optional.of(new Error("Loan amount field", "must not be negative"));
		}else{
			return Optional.empty();
		}
	}

	private Optional<Error> validateTerm(int passingTerm){
		if (passingTerm == 0) {
			return Optional.of(new Error("Passing term field", "must not be zero value or empty"));
		}else if (passingTerm < 0) {
			return Optional.of(new Error("Passing term field", "must not be negative"));
		}else{
			return Optional.empty();
		}
	}
}
