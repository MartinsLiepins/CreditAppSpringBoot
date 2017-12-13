package lv.mlproject17.CreditApp.database.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by marko on 2017.12.12..
 */

@Entity
@Table(name = "loan_application")
public class LoanApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "application_id")
	private Long id;

	@Column(nullable = false, name = "application_amount")
	private BigDecimal applicationAmount;

	@Column(nullable = false, length = 5, name = "passing_term_days")
	private int passingTerm;

	@Column(name = "application_state")
	private String applicationState;

	@Column(nullable = false, length = 30, name = "application_date")
	private String applicationDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@OneToMany(fetch=FetchType.LAZY, mappedBy = "loanApplication")
	private Set<Loan> loan = new HashSet<>(0);

	public LoanApplication(){
	}

	public LoanApplication(Customer customer, BigDecimal applicationAmount, int passingTerm,
	                       String applicationDate){
		this.customer = customer;
		this.applicationAmount = applicationAmount;
		this.passingTerm = passingTerm;
		this.applicationDate = applicationDate;
	}

	public LoanApplication(BigDecimal applicationAmount, int passingTerm,
	                       String applicationDate, Customer customer,
	                       Set<Loan> loan){
		this.applicationAmount = applicationAmount;
		this.passingTerm = passingTerm;
		this.applicationDate = applicationDate;
		this.customer = customer;
		this.loan = loan;
	}


	public Long getId(){
		return id;
	}
	public BigDecimal getApplicationAmount(){
		return applicationAmount;
	}
	public int getPassingTerm(){
		return passingTerm;
	}
	public String getApplicationState(){
		return applicationState;
	}
	public String getApplicationDate(){
		return applicationDate;
	}
	public Customer getCustomer(){
		return customer;
	}
	public Set<Loan> getLoan(){
		return loan;
	}

	public void setApplicationAmount(BigDecimal applicationAmount){
		this.applicationAmount = applicationAmount;
	}
	public void setPassingTerm(int passingTerm){
		this.passingTerm = passingTerm;
	}
	public void setApplicationState(String applicationState){
		this.applicationState = applicationState;
	}
	public void setApplicationDate(String applicationDate){
		this.applicationDate = applicationDate;
	}
	public void setCustomer(Customer customer){
		this.customer = customer;
	}
	public void setLoan(Set<Loan> loan){
		this.loan = loan;
	}
}
