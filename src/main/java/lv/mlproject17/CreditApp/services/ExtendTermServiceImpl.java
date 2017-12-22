package lv.mlproject17.CreditApp.services;

import lv.mlproject17.CreditApp.api.DateAndTime;
import lv.mlproject17.CreditApp.api.Response;
import lv.mlproject17.CreditApp.authentication.LoginUser;
import lv.mlproject17.CreditApp.database.model.ExtendedLoans;
import lv.mlproject17.CreditApp.database.model.Loan;
import lv.mlproject17.CreditApp.database.repository.ExtendedLoanRepository;
import lv.mlproject17.CreditApp.database.repository.LoanRepository;
import lv.mlproject17.CreditApp.dto.LoanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by marko on 2017.12.10..
 */

@Service
public class ExtendTermServiceImpl implements ExtendTermService {

	private static final BigDecimal EXTEND_LOAN_INTEREST_FACTOR_PER_WEEK = new BigDecimal(1.5/100);
	@Autowired
	LoanRepository loanRepository;
	@Autowired
	ExtendedLoanRepository extendedLoanRepository;
	@Autowired
	DateAndTime dateAndTime;

	@Override
	public Response extendLastUserLoan(int extendTermWeeks){

		Long id = loanRepository.getLastUserLoanIdByCustomerId(LoginUser.logInId());
		LoanDTO loanDto = buildLoanDto(loanRepository.getLoanByLoanId(id));

		if(loanDto.isLoanExtended()){
			return Response.serviceResponse("You cant extend this loan. Loan is returned", false);
		}
		if(loanDto.isLoanExtended()){
			loanDto.setAmount(extendedLoanRepository.
					findExtendedLoanAmount(loanDto.getId()));
			extendedLoanRepository.save(buildExtendedLoan(loanDto, extendTermWeeks));
			return Response.serviceResponse("Loan is extended", true);
		}else{
			extendedLoanRepository.save(buildExtendedLoan(loanDto, extendTermWeeks));
			loanRepository.updateLoanExtendedState(true, loanDto.getId());
			return Response.serviceResponse("Loan is extended", true);
		}
	}

	private LoanDTO buildLoanDto(Loan loan){
		LoanDTO dto = new LoanDTO();
		dto.setLoanExtended(loan.isLoanExtended());
		dto.setAmount(loan.getAmount());
		dto.setId(loan.getId());
		dto.setLoanReturnState(loan.isLoanReturnState());
		return dto;
	}

	private ExtendedLoans buildExtendedLoan(LoanDTO dto, int extendTermWeeks){
		ExtendedLoans extLoan = new ExtendedLoans();
		String dateTime = dateAndTime.getDateAndTimeString();
		extLoan.setExtendedAmount(calcAmountWithPercentsPerWeek(dto.getAmount(), extendTermWeeks));
		extLoan.setExtendTermWeeks(extendTermWeeks);
		extLoan.setLoanId(dto.getId());
		extLoan.setExtendPassingTermDate(dateTime);
		return extLoan;
	}
	private BigDecimal calcAmountWithPercentsPerWeek(BigDecimal amount, int extendTermWeeks){
		return amount.add(new BigDecimal(extendTermWeeks).
				multiply(amount.multiply(EXTEND_LOAN_INTEREST_FACTOR_PER_WEEK)));
	}




}
