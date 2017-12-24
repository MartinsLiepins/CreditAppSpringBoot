package lv.mlproject17.CreditApp.api;

import lv.mlproject17.CreditApp.dto.ViewUserLoansDTO;

import java.util.List;

/**
 * Created by marko on 2017.12.10..
 */
public class Response {

	private boolean success;
	private List<Error> errors;
	private List<Warning> warnings;
	private List<ViewUserLoansDTO> viewLoansList;

	public static Response successResponse(List<Warning> warnings){
		return new Response(true, null, warnings);
	}

	public static Response failResponse(List<Error> errors){
		return new Response(false, errors, null);
	}

	public static Response viewLoansResponse(List<ViewUserLoansDTO> viewLoansList){
	return new Response(viewLoansList);
	}

	public Response(boolean success, List<Error> errors, List<Warning> warnings){
		this.success = success;
		this.errors = errors;
		this.warnings = warnings;
	}

	public Response(List<ViewUserLoansDTO> viewList){
		this.viewLoansList = viewList;
	}

	public List<Error> getErrors(){
		return errors;
	}
	public List<Warning> getWarnings(){
		return warnings;
	}
	public List<ViewUserLoansDTO> getViewLoansList(){
		return viewLoansList;
	}

	public boolean isSuccess(){
		return success;
	}
	public boolean isFail(){
		return !success;
	}
}