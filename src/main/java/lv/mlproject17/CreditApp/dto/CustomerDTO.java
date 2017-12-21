package lv.mlproject17.CreditApp.dto;

/**
 * Created by marko on 2017.12.13..
 */
public class CustomerDTO {
	private Long id;
	private String name;
	private String password;
	private String date;

	public CustomerDTO(Long id, String name, String password, String date){
		this.id = id;
		this.name = name;
		this.password = password;
		this.date = date;
	}

	public CustomerDTO(Long id, String name, String password){
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public CustomerDTO(){
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getDate(){
		return date;
	}

	public void setDate(String date){
		this.date = date;
	}
}
