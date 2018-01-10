package lv.mlproject17.CreditApp.services.validators;

import lv.mlproject17.CreditApp.api.Error;
import lv.mlproject17.CreditApp.database.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RepayLoanValidator {

	@Autowired
	LoanRepository loanRepository;


	public List<Error> validate(BigDecimal returnAmount, Long customerId){
		List<Error> errors = new ArrayList<>();
		validateAmount(returnAmount).ifPresent(e -> errors.add(e));
		validateIfExistLoan(customerId).ifPresent(e -> errors.add(e));
		return errors;
	}

	private Optional<Error> validateIfExistLoan(Long customerId){
		if(notExist(customerId)){
			return Optional.of(new Error("Return loan", "You have not loans to return"));
		}else{
			return Optional.empty();
		}
	}

	private boolean notExist(Long loginId){
		return (!loanRepository.findLoansByCustomerId(loginId).isPresent());
	}

	private Optional<Error> validateAmount(BigDecimal loanAmount){
		if (loanAmount == null || loanAmount.equals("")) {
			return Optional.of(new Error("Loan Amount", "Must be not empty"));
		}else if (loanAmount.equals(BigDecimal.ZERO)) {
			return Optional.of(new Error("Loan Amount", "Must not by zero value"));
		}else if (loanAmount.compareTo(BigDecimal.ZERO)<0) {
			return Optional.of(new Error("Loan Amount", "Must not by negative"));
		}else{
			return Optional.empty();
		}
	}
}