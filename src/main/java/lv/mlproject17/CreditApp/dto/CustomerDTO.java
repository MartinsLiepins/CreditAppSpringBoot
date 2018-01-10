package lv.mlproject17.CreditApp.dto;

import java.time.LocalDateTime;

public class CustomerDTO {
	private Long id;
	private String name;
	private String password;
	private LocalDateTime date;

//	public CustomerDTO(Long id, String name, String password, String date){
//		this.id = id;
//		this.name = name;
//		this.password = password;
//		this.date = date;
//	}
//
//	public CustomerDTO(Long id, String name, String password){
//		this.id = id;
//		this.name = name;
//		this.password = password;
//	}

	public CustomerDTO(){
	}

	public Long getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	public String getPassword(){
		return password;
	}
	public LocalDateTime getDate(){
		return date;
	}

	public void setId(Long id){
		this.id = id;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public void setDate(LocalDateTime date){
		this.date = date;
	}
}
