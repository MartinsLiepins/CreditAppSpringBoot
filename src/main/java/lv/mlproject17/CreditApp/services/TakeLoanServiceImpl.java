package lv.mlproject17.CreditApp.services;

import lv.mlproject17.CreditApp.api.Response;
import lv.mlproject17.CreditApp.authentication.LoginUser;
import lv.mlproject17.CreditApp.database.model.Customer;
import lv.mlproject17.CreditApp.database.model.LoanApplication;
import lv.mlproject17.CreditApp.database.repository.LoanApplicationRepository;
import lv.mlproject17.CreditApp.database.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by marko on 2017.12.10..
 */

@Service
public class TakeLoanServiceImpl implements TakeLoanService {
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private LoanRepository loanRepository;


	public Response takeLoan(BigDecimal loanAmount, int passingTerm){


		LoanApplication loanApplication = new LoanApplication();
		loanApplication.setApplicationAmount(loanAmount);
		loanApplication.setPassingTerm(passingTerm);
		loanApplication.setApplicationDate(LocalDateTime.now().toString());

		Customer cs = new Customer();
		cs.setId(LoginUser.logInState());
		loanApplication.setCustomer(cs);

		loanApplicationRepository.save(loanApplication);









//		Loan loan = new Loan(loanAmount,LoginUser.logInState(), passingTerm,
//							false, LocalDateTime.now().toString());
//		loansRequestRepository.save(loan);

		return Response.actionSucess();
	}


}
