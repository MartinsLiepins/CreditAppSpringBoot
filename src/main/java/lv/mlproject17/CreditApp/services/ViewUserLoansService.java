package lv.mlproject17.CreditApp.services;

import lv.mlproject17.CreditApp.dto.ViewUserLoansDTO;

import java.util.List;

/**
 * Created by marko on 2017.12.08..
 */
public interface ViewUserLoansService {
	List<ViewUserLoansDTO> viewCustomerLoans(Long id);

//	List<Customer> getUserLoans(Long id);
//	List<Customer> getExtendedUserLoans(Long Id);
}
