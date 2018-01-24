package lv.mlproject17.CreditApp.database.model.builders;


import lv.mlproject17.CreditApp.database.model.Application;
import lv.mlproject17.CreditApp.threads.ApplicationStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ApplicationBuilder {
	private Long applicationId;
	private Long customerId;
	private BigDecimal applicationAmount;
	private BigDecimal approvedAmount;
	private int passingTermDays;
	private ApplicationStatus status;
	private BigDecimal interestFactor;
	private LocalDateTime date;

	private ApplicationBuilder(){}

	public static ApplicationBuilder createApplication(){
		return new ApplicationBuilder();
	}

	public Application build(){
		Application application = new Application();
		application.setId(applicationId);
		application.setCustomerId(customerId);
		application.setApplicationAmount(applicationAmount);
		application.setApprovedAmount(approvedAmount);
		application.setPassingTermDays(passingTermDays);
		application.setApplicationStatus(status);
		application.setInterestFactorDay(interestFactor);
		application.setApplicationDate(date);
		return application;
	}

	public ApplicationBuilder withApplicationId(Long applicationId){
		this.applicationId = applicationId;
		return this;
	}

	public ApplicationBuilder withCustomerId(Long customerId){
		this.customerId = customerId;
		return this;
	}

	public ApplicationBuilder withApplicationAmount(BigDecimal applicationAmount){
		this.applicationAmount = applicationAmount;
		return this;
	}

	public ApplicationBuilder withApprovedAmount(BigDecimal approvedAmount){
		this.approvedAmount = approvedAmount;
		return this;
	}

	public ApplicationBuilder withPassingTermDays(int passingTermDays){
		this.passingTermDays = passingTermDays;
		return this;
	}

	public ApplicationBuilder withApplicationStatus(ApplicationStatus status){
		this.status = status;
		return this;
	}

	public ApplicationBuilder withInterestFactor(BigDecimal interestFactor){
		this.interestFactor = interestFactor;
		return this;
	}

	public ApplicationBuilder withDate(LocalDateTime date){
		this.date = date;
		return this;
	}
}
