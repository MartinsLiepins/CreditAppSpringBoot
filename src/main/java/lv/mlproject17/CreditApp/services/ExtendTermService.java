package lv.mlproject17.CreditApp.services;

import lv.mlproject17.CreditApp.api.Error;
import lv.mlproject17.CreditApp.api.Response;
import lv.mlproject17.CreditApp.authentication.LoginUser;
import lv.mlproject17.CreditApp.database.model.ExtendedLoans;
import lv.mlproject17.CreditApp.database.model.Loan;
import lv.mlproject17.CreditApp.database.repository.ExtendedLoanRepository;
import lv.mlproject17.CreditApp.database.repository.LoanRepository;
import lv.mlproject17.CreditApp.services.validators.ExtendTermValidator;
import lv.mlproject17.CreditApp.services.validators.ServiceErrorMessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExtendTermService {

	private final BigDecimal EXTEND_LOAN_INTEREST_FACTOR_PER_WEEK = new BigDecimal(1.5/100);
	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	private ExtendedLoanRepository extendedLoanRepository;
	@Autowired
	private ExtendTermValidator validator;
	@Autowired
	private ServiceErrorMessageBuilder serviceErrorMessageBuilder;

	public Response extendLastUserLoan(int extendTermWeeks){

		List<Error> validationError = validator.validate(extendTermWeeks, LoginUser.logInId());

		if(!validationError.isEmpty()){
			return Response.failResponse(validationError);
		}

		Loan loan = loanRepository.findFirstByCustomerIdOrderByIdDesc(LoginUser.logInId()).get();

		if(loan.isLoanExtended()){
			BigDecimal extendedAmount = extendedLoanRepository.
					findFirstByLoanIdOrderByIdDesc(loan.getId()).getExtendedAmount();
			extendedLoanRepository.save(extendedLoanCreator(loan.getId(), extendedAmount, extendTermWeeks));
			return Response.successResponse(null);
		}
		extendedLoanRepository.save(extendedLoanCreator(loan.getId(), loan.getAmount(), extendTermWeeks));
		loanRepository.updateLoanExtendedState(true, loan.getId());
		return Response.successResponse(null);

	}

	private ExtendedLoans extendedLoanCreator(Long loanId, BigDecimal extendedAmount, int extendTermWeeks){
		ExtendedLoans extLoan = new ExtendedLoans();
		extLoan.setExtendedAmount(calcAmountWithPercentsPerWeek(extendedAmount, extendTermWeeks));
		extLoan.setExtendTermWeeks(extendTermWeeks);
		extLoan.setLoanId(loanId);
		extLoan.setExtendPassingTermDate(LocalDateTime.now());
		return extLoan;
	}

	private BigDecimal calcAmountWithPercentsPerWeek(BigDecimal amount, int extendTermWeeks){
		return amount.add(new BigDecimal(extendTermWeeks).
				multiply(amount.multiply(EXTEND_LOAN_INTEREST_FACTOR_PER_WEEK)));
	}
}
