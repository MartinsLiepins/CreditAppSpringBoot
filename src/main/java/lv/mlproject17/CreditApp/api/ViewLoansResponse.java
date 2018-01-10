package lv.mlproject17.CreditApp.api;

import lv.mlproject17.CreditApp.dto.ViewUserLoansDTO;

import java.util.List;

/**
 * Created by marko on 2018.01.07..
 */
public class ViewLoansResponse {
	private boolean success;
	private List<Error> errors;
	private List<ViewUserLoansDTO> viewLoansList;

	public static ViewLoansResponse failResponse(List<Error> errors){
		return new ViewLoansResponse(false, errors, null);
	}

	public static ViewLoansResponse successResponse(List<ViewUserLoansDTO> viewLoansList){
		return new ViewLoansResponse(true, null, viewLoansList);
	}

	public ViewLoansResponse(boolean success, List<Error> errors, List<ViewUserLoansDTO> viewList){
		this.success = success;
		this.errors = errors;
		this.viewLoansList = viewList;
	}

	public List<Error> getErrors(){
		return errors;
	}
	public List<ViewUserLoansDTO> getViewLoansList(){
		return viewLoansList;
	}

	public boolean getSuccess(){
		return success;
	}
	public boolean getFail(){
		return !success;
	}
}
