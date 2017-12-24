package lv.mlproject17.CreditApp.api;

/**
 * Created by marko on 2017.12.22..
 */
public class Error {
	private String field;
	private String errorMessage;

	public Error(String field, String errorMessage){
		this.field = field;
		this.errorMessage = errorMessage;
	}

	public String getField(){
		return field;
	}
	public String getErrorMessage(){
		return errorMessage;
	}

	public void setField(String field){
		this.field = field;
	}
	public void setErrorMessage(String errorMessage){
		this.errorMessage = errorMessage;
	}
}
