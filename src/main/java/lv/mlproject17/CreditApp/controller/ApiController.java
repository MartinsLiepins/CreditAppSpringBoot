package lv.mlproject17.CreditApp.controller;

import lv.mlproject17.CreditApp.api.Response;
import lv.mlproject17.CreditApp.api.ViewLoansResponse;
import lv.mlproject17.CreditApp.dto.LoanDto;
import lv.mlproject17.CreditApp.services.ExtendTermService;
import lv.mlproject17.CreditApp.services.RepayLoanService;
import lv.mlproject17.CreditApp.services.TakeLoanService;
import lv.mlproject17.CreditApp.services.ViewLoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quickloan/App")
public class ApiController {
	@Autowired
	private ExtendTermService extendTermService;
	@Autowired
	private TakeLoanService takeLoanService;
	@Autowired
	private ViewLoansService viewLoansService;
	@Autowired
	private RepayLoanService repayLoanService;

	@PostMapping(path="/loan")
	@ResponseBody
	Response takeLoan(
			@RequestBody LoanDto loanDto){
		return takeLoanService.takeLoan(loanDto.getAmount(), loanDto.getTerm());
	}

	@PostMapping(path="/extendLoan")
	@ResponseBody
	Response extendLoan(@RequestBody LoanDto loanDto){
		return extendTermService.extendLastUserLoan(loanDto.getTerm());
	}

	@RequestMapping(path="/viewLoans")
	@ResponseBody
	ViewLoansResponse viewLoans(){
				return viewLoansService.viewCustomerLoans();
			}

	@PostMapping(path="/repayLoan")
	@ResponseBody
	Response repayLoan(@RequestBody LoanDto loanDto){
		return repayLoanService.repayLoan(loanDto.getAmount());
	}
}






