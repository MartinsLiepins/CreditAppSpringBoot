package lv.mlproject17.CreditApp.api;

import java.util.List;

/**
 * Created by marko on 2017.12.10..
 */
public class Response {

	private boolean success;
	private List<Error> errors;

	public Response(boolean success, List<Error> errors){
		this.success = success;
		this.errors = errors;
	}

	public static Response actionSucess(){
		return new Response(true, null);
	}

	public static Response actionFail(){
		return new Response(false, null);
	}

	public boolean isSuccess(){
		return success;
	}

	public List<Error> getErrors(){
		return errors;
	}
}
