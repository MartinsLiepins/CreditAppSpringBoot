package lv.mlproject17.CreditApp.services.validators;

import lv.mlproject17.CreditApp.api.Error;
import lv.mlproject17.CreditApp.database.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ExtendTermValidator {
	private final int MAX_EXTEND_TERM_WEEKS = 48;
	@Autowired
	LoanRepository loanRepository;

	public List<Error> validate(int termWeeks, Long customerId){
		List<Error> errors = new ArrayList<>();

		validateIfLoansExistOrIsRepaid(customerId).ifPresent(e -> errors.add(e));
		validateTerm(termWeeks).ifPresent(e -> errors.add(e));
		return errors;
	}

	private Optional<Error> validateIfLoansExistOrIsRepaid(Long customerId){
		if(notExist(customerId)){
			return Optional.of(new Error("", "You have not loans"));
		}else if(isRepaid(customerId)){
				return Optional.of(new Error("", "All loans are repaid"));
		}else{
			return Optional.empty();
		}
	}

	private Optional<Error> validateTerm(int termWeeks){
		if (termWeeks == 0) {
			return Optional.of(new Error("Term in weeks field", "must be not empty"));
		}else if (termWeeks < 0) {
			return Optional.of(new Error("Term in weeks field", "must not by negative"));
		}else if (termWeeks > MAX_EXTEND_TERM_WEEKS) {
			return Optional.of(new Error("Term in weeks field", "must not by greater then "+
					MAX_EXTEND_TERM_WEEKS + " weeks"));
		}else{
			return Optional.empty();
		}
	}

	private boolean notExist(Long customerId){
		return (!loanRepository.findLoanByCustomerId(customerId).isPresent());
	}

	private boolean isRepaid(Long customerId){
		return loanRepository.findFirstByCustomerIdOrderByIdDesc(
				customerId).get().getLoanRepayState();
	}
}
