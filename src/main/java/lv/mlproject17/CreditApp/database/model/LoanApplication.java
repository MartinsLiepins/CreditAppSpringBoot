package lv.mlproject17.CreditApp.database.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by marko on 2017.12.12..
 */

@Entity
@Table(name = "loan_application")
public class LoanApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "application_id")
	private Long applicationId;

	@Column(nullable = false, name = "customer_id")
	private Long customerId;

	@Column(nullable = false, name = "application_amount")
	private BigDecimal applicationAmount;

	@Column(nullable = false, length = 5, name = "passing_term_days")
	private int passingTerm;

	@Column(name = "application_status")
	private String applicationStatus;

	@Column(nullable = false, length = 30, name = "application_date")
	private String applicationDate;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "customer_id", nullable = false)
//	private Customer customer;
//
//	@OneToMany(fetch=FetchType.LAZY, mappedBy = "loanApplication")
//	private Set<Loan> loan = new HashSet<>(0);

	public LoanApplication(){
	}

	public LoanApplication(Long customerId, BigDecimal applicationAmount,
	                       int passingTerm, String applicationDate){
		this.customerId = customerId;
		this.applicationAmount = applicationAmount;
		this.passingTerm = passingTerm;
		this.applicationStatus = applicationStatus;
		this.applicationDate = applicationDate;
	}

	//	public LoanApplication(Customer customer, BigDecimal applicationAmount, int passingTerm,
//	                       String applicationDate){
//		this.customer = customer;
//		this.applicationAmount = applicationAmount;
//		this.passingTerm = passingTerm;
//		this.applicationDate = applicationDate;
//	}

//	public LoanApplication(BigDecimal applicationAmount, int passingTerm,
//	                       String applicationDate, Customer customer,
//	                       Set<Loan> loan){
//		this.applicationAmount = applicationAmount;
//		this.passingTerm = passingTerm;
//		this.applicationDate = applicationDate;
//		this.customer = customer;
//		this.loan = loan;
//	}


	public Long getApplicationId(){
		return applicationId;
	}
	public BigDecimal getApplicationAmount(){
		return applicationAmount;
	}
	public int getPassingTerm(){
		return passingTerm;
	}
	public String getApplicationStatus(){
		return applicationStatus;
	}
	public String getApplicationDate(){
		return applicationDate;
	}
//	public Customer getCustomer(){
//		return customer;
//	}
//	public Set<Loan> getLoan(){
//		return loan;
//	}


	public Long getCustomerId(){
		return customerId;
	}
	public void setCustomerId(Long customerId){
		this.customerId = customerId;
	}

	public void setApplicationAmount(BigDecimal applicationAmount){
		this.applicationAmount = applicationAmount;
	}
	public void setPassingTerm(int passingTerm){
		this.passingTerm = passingTerm;
	}
	public void setApplicationStatus(String applicationStatus){
		this.applicationStatus = applicationStatus;
	}
	public void setApplicationDate(String applicationDate){
		this.applicationDate = applicationDate;
	}
//	public void setCustomer(Customer customer){
//		this.customer = customer;
//	}
//	public void setLoan(Set<Loan> loan){
//		this.loan = loan;
//	}
}
