package lv.mlproject17.CreditApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by martins.
 */

@Controller
public class WebController {

	@GetMapping(value="/")
	public String homepage(){
			return "index";
		}

}
