package lv.mlproject17.CreditApp.services;

import lv.mlproject17.CreditApp.api.Error;
import lv.mlproject17.CreditApp.api.Response;
import lv.mlproject17.CreditApp.authentication.LoginUser;
import lv.mlproject17.CreditApp.database.model.ExtendedLoans;
import lv.mlproject17.CreditApp.database.model.Loan;
import lv.mlproject17.CreditApp.database.repository.ExtendedLoanRepository;
import lv.mlproject17.CreditApp.database.repository.LoanRepository;
import lv.mlproject17.CreditApp.dto.LoanDTO;
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
	ExtendedLoanRepository extendedLoanRepository;
	@Autowired
	private ExtendTermValidator validator;
	@Autowired
	private ServiceErrorMessageBuilder serviceErrorMessageBuilder;

	public Response extendLastUserLoan(int extendTermWeeks){

		List<Error> validationError = validator.validate(extendTermWeeks, LoginUser.logInId());

		if(!validationError.isEmpty()){
			return Response.failResponse(validationError);
		}

		Long id = loanRepository.getLastLoanIdByCustomerId(LoginUser.logInId());
		LoanDTO loanDto = buildLoanDto(loanRepository.getLoanByLoanId(id));

		if(loanDto.isLoanExtended()){
			return Response.failResponse
					(serviceErrorMessageBuilder.buildMessage
							("", "You cant extend this loan. Loan is returned"));
		}
		if(loanDto.isLoanExtended()){
			loanDto.setAmount(extendedLoanRepository.
					findExtendedLoanAmount(loanDto.getId()));
			extendedLoanRepository.save(buildExtendedLoan(loanDto, extendTermWeeks));
			return Response.successResponse(null);
		}else{
			extendedLoanRepository.save(buildExtendedLoan(loanDto, extendTermWeeks));
			loanRepository.updateLoanExtendedState(true, loanDto.getId());
			return Response.successResponse(null);
		}
	}

	private LoanDTO buildLoanDto(Loan loan){
		LoanDTO dto = new LoanDTO();
		dto.setLoanExtended(loan.isLoanExtended());
		dto.setAmount(loan.getAmount());
		dto.setId(loan.getId());
		dto.setLoanReturnState(loan.getLoanRepayState());
		return dto;
	}

	private ExtendedLoans buildExtendedLoan(LoanDTO dto, int extendTermWeeks){
		ExtendedLoans extLoan = new ExtendedLoans();
		extLoan.setExtendedAmount(calcAmountWithPercentsPerWeek(dto.getAmount(), extendTermWeeks));
		extLoan.setExtendTermWeeks(extendTermWeeks);
		extLoan.setLoanId(dto.getId());
		extLoan.setExtendPassingTermDate(LocalDateTime.now());
		return extLoan;
	}

	private BigDecimal calcAmountWithPercentsPerWeek(BigDecimal amount, int extendTermWeeks){
		return amount.add(new BigDecimal(extendTermWeeks).
				multiply(amount.multiply(EXTEND_LOAN_INTEREST_FACTOR_PER_WEEK)));
	}




}
