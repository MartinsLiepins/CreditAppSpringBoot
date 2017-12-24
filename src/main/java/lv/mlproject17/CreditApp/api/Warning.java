package lv.mlproject17.CreditApp.api;

/**
 * Created by marko on 2017.12.24..
 */
public class Warning {
	private String warningMessage;

	public Warning(String warningMessage){
		this.warningMessage = warningMessage;
	}

	public String getWarningMessage(){
		return warningMessage;
	}
	public void setWarningMessage(String warningMessage){
		this.warningMessage = warningMessage;
	}
}
