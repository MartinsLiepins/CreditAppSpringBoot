package lv.mlproject17.CreditApp.services;

import lv.mlproject17.CreditApp.api.Error;
import lv.mlproject17.CreditApp.api.ViewLoansResponse;
import lv.mlproject17.CreditApp.authentication.LoginUser;
import lv.mlproject17.CreditApp.database.model.ExtendedLoans;
import lv.mlproject17.CreditApp.database.model.Loan;
import lv.mlproject17.CreditApp.database.repository.ExtendedLoanRepository;
import lv.mlproject17.CreditApp.database.repository.LoanRepository;
import lv.mlproject17.CreditApp.dto.ViewUserLoansDTO;
import lv.mlproject17.CreditApp.services.validators.ViewLoansValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ViewLoansService {
	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	ExtendedLoanRepository extendedLoanRepository;
	@Autowired
	ViewLoansValidator validator;

	public ViewLoansResponse viewCustomerLoans(){

		List<Error> validationError = validator.validateViewLoans(LoginUser.logInId());

		if(!validationError.isEmpty()){
			return ViewLoansResponse.failResponse(validationError);
		}

		List<Loan> userLoans = loanRepository.findLoanByCustomerId(LoginUser.logInId()).get();
		List<ViewUserLoansDTO> userLoansDTO = new ArrayList<>();

		for(Loan userLoan : userLoans){
			ViewUserLoansDTO nextLoan = new ViewUserLoansDTO();

			nextLoan.setAmount(userLoan.getAmount());
			nextLoan.setPassingTermDays(userLoan.getPassingTermDays());
			nextLoan.setLoanIssueDate(userLoan.getIssueDate());
			nextLoan.setLoanReturnState(userLoan.getLoanRepayState());
			if(userLoan.isLoanExtended()){
				List<ExtendedLoans> extLoans =
						extendedLoanRepository.findExtendedLoansByLoanId(userLoan.getId());
				List<ExtendedLoans> extLoansList = new ArrayList<>();

				for(ExtendedLoans extLoan : extLoans){
					ExtendedLoans extendedLoans = new ExtendedLoans();

					extendedLoans.setExtendedAmount(extLoan.getExtendedAmount());
					extendedLoans.setExtendTermWeeks(extLoan.getExtendTermWeeks());
					extendedLoans.setExtendPassingTermDate(extLoan.getExtendPassingTermDate());
					extLoansList.add(extendedLoans);
				}
				nextLoan.setExtendedLoans(extLoansList);
			}
			userLoansDTO.add(nextLoan);
		}
		return ViewLoansResponse.successResponse(userLoansDTO);
	}
}
