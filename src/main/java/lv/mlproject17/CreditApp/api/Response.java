package lv.mlproject17.CreditApp.api;

/**
 * Created by marko on 2017.12.10..
 */
public class Response {

	private String message;
	private boolean applicationCondition;

	public Response(String message, boolean applicationCondition){
		this.message = message;
		this.applicationCondition = applicationCondition;
	}

	public static Response serviceResponse(String string, boolean actionCondiotion){
		return new Response(string, actionCondiotion);
	}

	public String getMessage(){
		return message;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public boolean isApplicationCondition(){
		return applicationCondition;
	}

	public void setApplicationCondition(boolean applicationCondition){
		this.applicationCondition = applicationCondition;
	}
}
