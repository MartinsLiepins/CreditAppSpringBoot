package lv.mlproject17.CreditApp.authentication;

/**
 * Created by marko on 2017.12.11..
 */

public class LoginUser {
	private static Long id;

	public LoginUser(Long id){
		this.id = id;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public static Long logInState(){
//		return this.id;
	return id;
	}
}
