package lv.mlproject17.CreditApp.database.model;

import lv.mlproject17.CreditApp.threads.ApplicationStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan_application")
public class Application {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;


	@Column(nullable = false, name = "customer_id")
	private Long customerId;

	@Column(nullable = false, name = "application_amount")
	private BigDecimal applicationAmount;

	@Column(nullable = false, name = "approved_amount")
	private BigDecimal approvedAmount;

	@Column(nullable = false, length = 5, name = "passing_term_days")
	private int passingTermDays;

	@Column(name = "application_status")
	private String applicationStatus;

	@Column(name = "interest_factor")
	private BigDecimal interestFactorDay;

	@Column(nullable = false, length = 80, name = "application_date")
	private String applicationDate;

	public Application(){
	}

	public Long getId(){
		return id;
	}
	public Long getCustomerId(){
		return customerId;
	}
	public BigDecimal getApplicationAmount(){
		return applicationAmount;
	}
	public BigDecimal getApprovedAmount(){
		return approvedAmount;
	}
	public int getPassingTermDays(){
		return passingTermDays;
	}
	public ApplicationStatus getApplicationStatus(){
		return ApplicationStatus.valueOf(applicationStatus);
	}
	public BigDecimal getInterestFactorDay(){
		return interestFactorDay;
	}
	public LocalDateTime getApplicationDate(){
		return LocalDateTime.parse(applicationDate);
	}

	public void setId(Long id){
		this.id = id;
	}
	public void setCustomerId(Long customerId){
		this.customerId = customerId;
	}
	public void setApplicationAmount(BigDecimal applicationAmount){
		this.applicationAmount = applicationAmount;
	}
	public void setApprovedAmount(BigDecimal approvedAmount){
		this.approvedAmount = approvedAmount;
	}
	public void setPassingTermDays(int passingTermDays){
		this.passingTermDays = passingTermDays;
	}
	public void setApplicationStatus(ApplicationStatus applicationStatus){
		this.applicationStatus = applicationStatus.toString();
	}
	public void setInterestFactorDay(BigDecimal interestFactorDay){
		this.interestFactorDay = interestFactorDay;
	}
	public void setApplicationDate(LocalDateTime applicationDate){
		this.applicationDate = applicationDate.toString();
	}
}
