package lv.mlproject17.CreditApp.services.validators;

import lv.mlproject17.CreditApp.api.Error;
import lv.mlproject17.CreditApp.database.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by marko on 2017.12.22..
 */

@Component
public class ExtendTermValidator {
	@Autowired
	LoanRepository loanRepository;

	public List<Error> validate(int termWeeks, Long customerId){
		List<Error> errors = new ArrayList<>();
		validateTerm(termWeeks).ifPresent(e -> errors.add(e));
		validateIfLoansExist(customerId).ifPresent(e -> errors.add(e));
		return errors;
	}

	private Optional<Error> validateIfLoansExist(Long customerId){
		if(notExist(customerId)){
			return Optional.of(new Error("Extend loan", "You have not loans"));
		}else{
			return Optional.empty();
		}
	}

	private boolean notExist(Long customerId){
		return (!loanRepository.findLoansByCustomerId(customerId).isPresent());
	}

	private Optional<Error> validateTerm(int termWeeks){
		if (termWeeks == 0) {
			return Optional.of(new Error("Extend term weeks", "Mst be not empty"));
		}else if (termWeeks < 0) {
			return Optional.of(new Error("Extend term weeks", "Must not by negative"));
		}else if (termWeeks > 48) {
			return Optional.of(new Error("Extend term weeks", "Must not by greater then 48"));
		}else{
			return Optional.empty();
		}
	}
}
