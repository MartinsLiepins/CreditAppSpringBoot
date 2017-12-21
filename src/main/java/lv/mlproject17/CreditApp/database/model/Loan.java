package lv.mlproject17.CreditApp.database.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by marko on 2017.12.08..
 */

@Entity
@Table(name = "loan")
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "loan_issue_id")
	private Long id;

	@Column(nullable = false, name = "customer_id")
	private Long customerId;

	@Column(nullable = false, name = "amount")
	private BigDecimal amount;

	@Column(nullable = false, length = 5, name = "passing_term_days")
	private int passingTerm;

	@Column(name = "loan_extended_state")
	private boolean loanExtended;

	@Column(nullable = false, length = 30, name = "loan_issue_date")
	private String issueDate;

	@Column(name = "loan_return_state")
	private boolean loanReturnState;

//	@ManyToOne
//	@JoinColumn(name = "customer_id", nullable = false)
//	private Customer customer;
//
//	@ManyToOne
//	@JoinColumn(name = "application_id", nullable = false)
//	private LoanApplication loanApplication;
//
//	@OneToMany(fetch=FetchType.LAZY, mappedBy = "loan")
//	private Set<ExtendedLoans> extendedLoans = new HashSet<>(0);

	public Loan(){}

	public Loan(Long customerId, BigDecimal amount,
	            int passingTerm, boolean loanExtended,
	            String issueDate){
		this.customerId = customerId;
		this.amount = amount;
		this.passingTerm = passingTerm;
		this.loanExtended = loanExtended;
		this.issueDate = issueDate;
	}

	//	public Loan(Customer customer, LoanApplication loanApplication,
//	            BigDecimal amount, int passingTerm, boolean loanExtended,
//	            String loanIssueDate){
//		this.customer = customer;
//		this.loanApplication = loanApplication;
//		this.amount = amount;
//		this.passingTerm = passingTerm;
//		this.loanExtended = loanExtended;
//		this.issueDate = loanIssueDate;
//	}

	public Long getId(){
		return id;
	}
//	public Customer getCustomer(){
//		return customer;
//	}
//	public LoanApplication getLoanApplication(){
//		return loanApplication;
//	}
	public BigDecimal getAmount(){
		return amount;
	}
	public int getPassingTerm(){
		return passingTerm;
	}
	public boolean isLoanExtended(){
		return loanExtended;
	}
	public String getIssueDate(){
		return issueDate;
	}
	public boolean isLoanReturnState(){
		return loanReturnState;
	}

	public Long getCustomerId(){
		return customerId;
	}
	public void setCustomerId(Long customerId){
		this.customerId = customerId;
	}



	//	public void setCustomer(Customer customer){
//		this.customer = customer;
//	}
//	public void setLoanApplication(LoanApplication loanApplication){
//		this.loanApplication = loanApplication;
//	}

	public void setId(Long id){
		this.id = id;
	}
	public void setAmount(BigDecimal amount){
		this.amount = amount;
	}
	public void setPassingTerm(int passingTerm){
		this.passingTerm = passingTerm;
	}
	public void setLoanExtended(boolean loanExtended){
		this.loanExtended = loanExtended;
	}
	public void setIssueDate(String takeLoanDate){
		this.issueDate = takeLoanDate;
	}
	public void setLoanReturnState(boolean loanReturnState){
		this.loanReturnState = loanReturnState;
	}
}
