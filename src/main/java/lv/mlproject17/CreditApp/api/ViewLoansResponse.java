package lv.mlproject17.CreditApp.api;

import lv.mlproject17.CreditApp.dto.ViewUserLoansDto;

import java.util.List;

/**
 * Created by marko on 2018.01.07..
 */
public class ViewLoansResponse {
	private String status;
	private List<Error> errors;
	private List<ViewUserLoansDto> viewLoansList;

	public static ViewLoansResponse failResponse(List<Error> errors){
		return new ViewLoansResponse("Error", errors, null);
	}

	public static ViewLoansResponse successResponse(List<ViewUserLoansDto> viewLoansList){
		return new ViewLoansResponse("Done", null, viewLoansList);
	}

	public ViewLoansResponse(String status, List<Error> errors, List<ViewUserLoansDto> viewList){
		this.status = status;
		this.errors = errors;
		this.viewLoansList = viewList;
	}

	public List<Error> getErrors(){
		return errors;
	}
	public List<ViewUserLoansDto> getViewLoansList(){
		return viewLoansList;
	}

	public String getStatus(){
		return status;
	}
}
