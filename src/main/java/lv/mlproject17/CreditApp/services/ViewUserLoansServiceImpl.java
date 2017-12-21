package lv.mlproject17.CreditApp.services;

import lv.mlproject17.CreditApp.authentication.LoginUser;
import lv.mlproject17.CreditApp.database.model.ExtendedLoans;
import lv.mlproject17.CreditApp.database.model.Loan;
import lv.mlproject17.CreditApp.database.repository.ExtendedLoanRepository;
import lv.mlproject17.CreditApp.database.repository.LoanRepository;
import lv.mlproject17.CreditApp.dto.ExtendedLoansDTO;
import lv.mlproject17.CreditApp.dto.ViewUserLoansDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marko on 2017.12.08..
 */
@Service
public class ViewUserLoansServiceImpl implements ViewUserLoansService {
	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	ExtendedLoanRepository extendedLoanRepository;

	@Override
	public List<ViewUserLoansDTO> viewCustomerLoans(Long id){

		List<Loan> userLoans = loanRepository.getLoansByCustomerId(LoginUser.logInId());
		List<ViewUserLoansDTO> userLoansDTO = new ArrayList<>();

		for(Loan userLoan : userLoans){
			ViewUserLoansDTO nextLoanDto = new ViewUserLoansDTO();
			nextLoanDto.setAmount(userLoan.getAmount());
			nextLoanDto.setPassingTerm(userLoan.getPassingTerm());
			nextLoanDto.setLoanIssueDate(userLoan.getIssueDate());
			if(userLoan.isLoanExtended()){
				List<ExtendedLoans> extLoans =
						extendedLoanRepository.findAllUserExtendedLoans(userLoan.getId());
				List<ExtendedLoansDTO> extLoansListDto = new ArrayList<>();
				for(ExtendedLoans extLoan : extLoans){
					ExtendedLoansDTO extendedLoansDTO = new ExtendedLoansDTO();
					extendedLoansDTO.setExtendedAmount(extLoan.getExtendedAmount());
					extendedLoansDTO.setExtendTermWeeks(extLoan.getExtendTermWeeks());
					extendedLoansDTO.setExtendTermDate(extLoan.getExtendPassingTermDate());
					extLoansListDto.add(extendedLoansDTO);
				}
				nextLoanDto.setExtendedLoans(extLoansListDto);
			}
			userLoansDTO.add(nextLoanDto);
		}
		return userLoansDTO;
	}
}
