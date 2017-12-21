package lv.mlproject17.CreditApp.database.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by marko on 2017.12.08..
 */

@Entity
@Table(name = "extended_loans")
public class ExtendedLoans {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "extended_id")
	private Long id;

	@Column(name = "loan_id")
	private Long loanId;

	@Column(nullable = false, name = "extended_loan_amount")
	private BigDecimal extendedAmount;

	@Column(nullable = false, name = "extend_passing_term_weeks")
	private int extendTermWeeks;

	@Column(nullable = false, length = 30, name = "extend_passing_term_date")
	private String extendPassingTermDate;

//	@ManyToOne
//	@JoinColumn(name = "loan_issue_id", nullable = false)
//	private Loan loan;

	public ExtendedLoans(){}

//	public ExtendedLoans(BigDecimal extendedAmount,
//	                    int extendTermWeeks, String extendPassingTermDate){
//		this.extendedAmount = extendedAmount;
//		this.extendTermWeeks = extendTermWeeks;
//		this.extendPassingTermDate = extendPassingTermDate;
//	}

	public Long getId(){
		return id;
	}
//	public Loan getLoan(){
//		return loan;
//	}
	public BigDecimal getExtendedAmount(){
		return extendedAmount;
	}
	public int getExtendTermWeeks(){
		return extendTermWeeks;
	}
	public String getExtendPassingTermDate(){
		return extendPassingTermDate;
	}


	public void setLoanId(Long loanId){
		this.loanId = loanId;
	}
	public Long getLoanId(){
		return loanId;
	}

	//		public void setLoan(Loan loan){
//		this.loan = loan;
//	}
	public void setExtendedAmount(BigDecimal extendedAmount){
		this.extendedAmount = extendedAmount;
	}
	public void setExtendTermWeeks(int extendTermWeeks){
		this.extendTermWeeks = extendTermWeeks;
	}
	public void setExtendPassingTermDate(String extendPassingTermDate){
		this.extendPassingTermDate = extendPassingTermDate;
	}
}
