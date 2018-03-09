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


	public List<Error> validate(BigDecimal repayAmount, Long customerId){
		List<Error> errors = new ArrayList<>();
		validateAmount(repayAmount).ifPresent(e -> errors.add(e));
		validateIfLoanExistOrIsRepaid(customerId).ifPresent(e -> errors.add(e));
		return errors;
	}

	private Optional<Error> validateAmount(BigDecimal repayAmount){
		if (repayAmount == null || repayAmount.equals("")) {
			return Optional.of(new Error("Repay amount field", "must not be empty"));
		}else if (repayAmount.equals(BigDecimal.ZERO)) {
			return Optional.of(new Error("Repay amount field", "must not be zero value"));
		}else if (repayAmount.compareTo(BigDecimal.ZERO)<0) {
			return Optional.of(new Error("Repay amount field", "must not be negative"));
		}else{
			return Optional.empty();
		}
	}

	private Optional<Error> validateIfLoanExistOrIsRepaid(Long customerId){
		if(notExist(customerId)){
			return Optional.of(new Error("", "You have not loans"));
		}else if(isRepaid(customerId)){
			return Optional.of(new Error("", "All loans are repaid"));
		}else{
			return Optional.empty();
		}
	}

	private boolean notExist(Long customerId){
		return (!loanRepository.findFirstByCustomerIdOrderByIdDesc(customerId).
				isPresent());
	}

	private boolean isRepaid(Long customerId){
		return (loanRepository.findFirstByCustomerIdOrderByIdDesc(customerId).
				get().getLoanRepayState());
	}
}