package lv.mlproject17.CreditApp.services;

import lv.mlproject17.CreditApp.api.Response;
import lv.mlproject17.CreditApp.authentication.LoginUser;
import lv.mlproject17.CreditApp.database.model.Loan;
import lv.mlproject17.CreditApp.database.repository.ExtendedLoanRepository;
import lv.mlproject17.CreditApp.database.repository.LoanApplicationRepository;
import lv.mlproject17.CreditApp.database.repository.LoanRepository;
import lv.mlproject17.CreditApp.dto.LoanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by marko on 2017.12.20..
 */

@Service
public class ReturnLoanServiceImpl implements ReturnLoanService{

	@Autowired
	private ExtendedLoanRepository extendedLoanRepository;
	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Override
	public Response returnLoan(BigDecimal returnAmount){
		Long loanId = loanRepository.getLastUserLoanIdByCustomerId(LoginUser.logInId());

		LoanDTO loanDto = LoanDtoBuilder(loanRepository.getLoanByLoanId(loanId));

		if(loanDto.isLoanExtended()){
			loanDto.setAmount(extendedLoanRepository.findExtendedLoanAmount(loanDto.getId()));
			if(loanDto.getAmount().compareTo(returnAmount) == 0){
				loanRepository.updateLoanReturnState(true, loanDto.getId());
				return Response.serviceResponse("Loan is returned", true);

			}else if(loanDto.getAmount().compareTo(returnAmount) == 1){
				return Response.serviceResponse("Return loan amount is to small, loan is not returned", false);
			}else{
				return Response.serviceResponse("Return loan amount is to large, loan is not returned", false);
			}
		}else{
			if(loanDto.getAmount().compareTo(returnAmount) == 0){
				loanRepository.updateLoanReturnState(true, loanDto.getId());
				return Response.serviceResponse("Loan is returned", true);
			}else if(loanDto.getAmount().compareTo(returnAmount) == 1){
				return Response.serviceResponse("Return loan amount is to small, loan is not returned", false);
			}else{
				return Response.serviceResponse("Return loan amount is to large, loan is not returned", false);
			}
		}
//		return Response.serviceResponse("Service error", false);
	}

	private LoanDTO LoanDtoBuilder(Loan loan){
		LoanDTO loanDto = new LoanDTO();

		loanDto.setAmount(loan.getAmount());
		loanDto.setIssueDate(loan.getIssueDate());
		loanDto.setLoanExtended(loan.isLoanExtended());
		loanDto.setId(loan.getId());
		return loanDto;
	}
}
