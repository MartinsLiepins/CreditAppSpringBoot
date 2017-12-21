package lv.mlproject17.CreditApp.services;

import lv.mlproject17.CreditApp.api.Response;

/**
 * Created by marko on 2017.12.10..
 */
public interface ExtendTermService {
	Response extendLastUserLoan(int extendTermWeeks);

}
