package lv.mlproject17.CreditApp.database.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

	@Column(nullable = false, name = "approved_amount")
	private BigDecimal approvedAmount;

	@Column(nullable = false, length = 5, name = "passing_term_days")
	private int passingTermDays;

	@Column(name = "application_status")
	private String applicationStatus;

	@Column(nullable = false, length = 80, name = "application_date")
	private String applicationDate;

	public LoanApplication(){
	}

	public Long getApplicationId(){
		return applicationId;
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
	public String getApplicationStatus(){
		return applicationStatus;
	}
	public LocalDateTime getApplicationDate(){
		return LocalDateTime.parse(applicationDate);
	}

	public void setApplicationId(Long applicationId){
		this.applicationId = applicationId;
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
	public void setApplicationStatus(String applicationStatus){
		this.applicationStatus = applicationStatus;
	}
	public void setApplicationDate(LocalDateTime applicationDate){
		this.applicationDate = applicationDate.toString();
	}
}
