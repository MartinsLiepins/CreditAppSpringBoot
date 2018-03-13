package lv.mlproject17.CreditApp.api;

import java.util.List;

/**
 * Created by marko on 2017.12.10..
 */
public class Response {

	private String status;
	private List<Error> errors;
	private List<Warning> warnings;

	public static Response successResponse(List<Warning> warnings){
		if(warnings == null){
			return new Response("Done", null, null);
		}else{
			return new Response("Warning", null, warnings);
		}
	}

	public static Response failResponse(List<Error> errors){
		return new Response("Error", errors, null);
	}

	public Response(String status, List<Error> errors, List<Warning> warnings){
		this.status = status;
		this.errors = errors;
		this.warnings = warnings;
	}


	public List<Error> getErrors(){
		return errors;
	}
	public List<Warning> getWarnings(){
		return warnings;
	}
	public String getStatus(){
		return status;
	}
}