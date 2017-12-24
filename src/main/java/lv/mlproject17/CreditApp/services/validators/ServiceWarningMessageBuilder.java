package lv.mlproject17.CreditApp.services.validators;

import lv.mlproject17.CreditApp.api.Warning;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceWarningMessageBuilder {
	public List<Warning> buildMessage(String warningMessage){
		Warning warning = new Warning(warningMessage);
		List<Warning> warnings = new ArrayList<>();
		warnings.add(warning);
		return warnings;
	}
}
