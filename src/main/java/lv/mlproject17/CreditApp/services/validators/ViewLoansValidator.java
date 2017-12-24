package lv.mlproject17.CreditApp.services.validators;

import lv.mlproject17.CreditApp.api.Error;
import lv.mlproject17.CreditApp.database.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ViewLoansValidator {

	@Autowired
	private LoanRepository loanRepository;

	public List<Error> validateViewLoans(Long customerId){
		List<Error> errors = new ArrayList<>();
		validateIfExistLoan(customerId).ifPresent(e -> errors.add(e));
		return errors;
	}

	private Optional<Error> validateIfExistLoan(Long customerId){
		if(notExist(customerId)){
			return Optional.of(new Error("View loan", "You have not loan to view"));
		}else{
			return Optional.empty();
		}
	}

	private boolean notExist(Long loginId){
		return (!loanRepository.findLoansByCustomerId(loginId).isPresent());
	}
}
