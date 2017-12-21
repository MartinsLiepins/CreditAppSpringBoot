package lv.mlproject17.CreditApp.database.model;


import javax.persistence.*;

/**
 * Created by marko on 2017.12.08..
 */

@Entity
@Table(name = "customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_id")
	private Long id;

	@Column(nullable = false, length = 20, name = "name")
	private String name;

	@Column(nullable = false, name = "password")
	private String password;

	@Column(nullable = false, length = 30, name = "registration_date")
	private String registrationDate;

//	@OneToMany(fetch=FetchType.LAZY, mappedBy = "customer")
//	private Set<LoanApplication> loanApplication = new HashSet<>(0);

	public Customer(){
	}

	public Customer(String name, String password, String date){
		this.name = name;
		this.password = password;
		this.registrationDate = date;
	}

//	public Customer(String name, String password,
//	                String registrationDate,
//	                Set<LoanApplication> loanApplication){
//		this.name = name;
//		this.password = password;
//		this.registrationDate = registrationDate;
//		this.loanApplication = loanApplication;
//	}

	public Long getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	public String getPassword(){
		return password;
	}
	public String getRegistrationDate(){
		return registrationDate;
	}
//	public void setLoanApplication(Set<LoanApplication> loanApplication){
//		this.loanApplication = loanApplication;
//	}


	public void setId(Long id){
		this.id = id;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public void setRegistrationDate(String registrationDate){
		this.registrationDate = registrationDate;
	}
//	public Set<LoanApplication> getLoanApplication(){
//		return loanApplication;
//	}
}
