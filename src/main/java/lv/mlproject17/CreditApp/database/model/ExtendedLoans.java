package lv.mlproject17.CreditApp.database.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

	@Column(nullable = false, length = 80, name = "extend_passing_term_date")
	private String extendPassingTermDate;

	public ExtendedLoans(){}

	public Long getId(){
		return id;
	}
	public Long getLoanId(){
		return loanId;
	}
	public BigDecimal getExtendedAmount(){
		return extendedAmount;
	}
	public int getExtendTermWeeks(){
		return extendTermWeeks;
	}
	public LocalDateTime getExtendPassingTermDate(){
		return LocalDateTime.parse(extendPassingTermDate);
	}


	public void setLoanId(Long loanId){
		this.loanId = loanId;
	}
	public void setExtendedAmount(BigDecimal extendedAmount){
		this.extendedAmount = extendedAmount;
	}
	public void setExtendTermWeeks(int extendTermWeeks){
		this.extendTermWeeks = extendTermWeeks;
	}
	public void setExtendPassingTermDate(LocalDateTime extendPassingTermDate){
		this.extendPassingTermDate = extendPassingTermDate.toString();
	}
}
