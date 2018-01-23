package lv.mlproject17.CreditApp.database.model;


import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by marko on 2017.12.08..
 */

@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(nullable = false, length = 20, name = "email")
	private String email;

	@Column(nullable = false, name = "password")
	private String password;

//	@DateTimeFormat(pattern = "dd.MM.yyyy - HH:mm")
	@Column(nullable = false, name = "registration_date")
	private String registrationDate;

	public Customer(){
	}

	public Long getId(){
		return id;
	}
	public String getEmail(){
		return email;
	}
	public String getPassword(){
		return password;
	}
	public LocalDateTime getRegistrationDate(){
		return LocalDateTime.parse(registrationDate);
	}

	public void setId(Long id){
		this.id = id;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public void setRegistrationDate(LocalDateTime registrationDate){
		this.registrationDate = registrationDate.toString();
	}
}
