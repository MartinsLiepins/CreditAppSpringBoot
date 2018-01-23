package lv.mlproject17.CreditApp.services;

import lv.mlproject17.CreditApp.api.Error;
import lv.mlproject17.CreditApp.api.Response;
import lv.mlproject17.CreditApp.authentication.LoginUser;
import lv.mlproject17.CreditApp.database.model.Loan;
import lv.mlproject17.CreditApp.database.repository.ApplicationRepository;
import lv.mlproject17.CreditApp.database.repository.ExtendedLoanRepository;
import lv.mlproject17.CreditApp.database.repository.LoanRepository;
import lv.mlproject17.CreditApp.services.validators.RepayLoanValidator;
import lv.mlproject17.CreditApp.services.validators.ServiceErrorMessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by marko on 2017.12.20..
 */

@Service
public class RepayLoanService {

	@Autowired
	private ExtendedLoanRepository extendedLoanRepository;
	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	private ApplicationRepository applicationRepository;
	@Autowired
	private RepayLoanValidator validator;
	@Autowired
	private ServiceErrorMessageBuilder serviceErrorMessageBuilder;

	public Response repayLoan(BigDecimal repayAmount){
		List<Error> validationError = validator.validate(repayAmount, LoginUser.logInId());

		if(!validationError.isEmpty()){
			return Response.failResponse(validationError);
		}

		Loan loan = loanRepository.findFirstByCustomerIdOrderByIdDesc(
				LoginUser.logInId()).get();

		if(loan.isLoanExtended()){
			loan.setAmount(extendedLoanRepository.findFirstByLoanIdOrderByIdDesc(
					loan.getId()).getExtendedAmount());
		}
		if(loan.getAmount().compareTo(repayAmount) == 0){
			loanRepository.updateLoanRepayState(true, loan.getId());
			return Response.successResponse(null);

		}else if(loan.getAmount().compareTo(repayAmount) == 1){
			return Response.failResponse
					(serviceErrorMessageBuilder.buildMessage(
							"Return amount", "Return loan amount is to small, loan is not returned"));
		}else{
			return Response.failResponse(
					serviceErrorMessageBuilder.buildMessage(
							"Return amount", "Return loan amount is to large, loan is not returned"));
		}
	}
}
