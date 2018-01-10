package lv.mlproject17.CreditApp.services;

import lv.mlproject17.CreditApp.api.Error;
import lv.mlproject17.CreditApp.api.Response;
import lv.mlproject17.CreditApp.authentication.LoginUser;
import lv.mlproject17.CreditApp.database.model.Loan;
import lv.mlproject17.CreditApp.database.repository.ExtendedLoanRepository;
import lv.mlproject17.CreditApp.database.repository.LoanApplicationRepository;
import lv.mlproject17.CreditApp.database.repository.LoanRepository;
import lv.mlproject17.CreditApp.dto.LoanDTO;
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
	private LoanApplicationRepository loanApplicationRepository;
	@Autowired
	private RepayLoanValidator validator;
	@Autowired
	private ServiceErrorMessageBuilder serviceErrorMessageBuilder;

	public Response returnLoan(BigDecimal returnAmount){
		List<Error> validationError = validator.validate(returnAmount, LoginUser.logInId());

		if(!validationError.isEmpty()){
			return Response.failResponse(validationError);
		}

		Long loanId = loanRepository.getLastLoanIdByCustomerId(LoginUser.logInId());

		LoanDTO loanDto = LoanDtoBuilder(loanRepository.getLoanByLoanId(loanId));

		if(loanDto.isLoanExtended()){
			loanDto.setAmount(extendedLoanRepository.findExtendedLoanAmount(loanDto.getId()));
			if(loanDto.getAmount().compareTo(returnAmount) == 0){
				loanRepository.updateLoanRepayState(true, loanDto.getId());
				return Response.successResponse(null);

			}else if(loanDto.getAmount().compareTo(returnAmount) == 1){
				return Response.failResponse
						(serviceErrorMessageBuilder.buildMessage
								("Return amount", "Return loan amount is to small, loan is not returned"));
			}else{
				return Response.failResponse
						(serviceErrorMessageBuilder.buildMessage
								("Return amount", "Return loan amount is to large, loan is not returned"));
			}
		}else{
			if(loanDto.getAmount().compareTo(returnAmount) == 0){
				loanRepository.updateLoanRepayState(true, loanDto.getId());
				return Response.successResponse(null);

			}else if(loanDto.getAmount().compareTo(returnAmount) == 1){
				return Response.failResponse
						(serviceErrorMessageBuilder.buildMessage
								("Return amount", "Return loan amount is to small, loan is not returned"));
			}else{
				return Response.failResponse
						(serviceErrorMessageBuilder.buildMessage
								("Return amount", "Return loan amount is to large, loan is not returned"));
			}
		}
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
