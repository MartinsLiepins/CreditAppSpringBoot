package lv.mlproject17.CreditApp.services;

import lv.mlproject17.CreditApp.api.Error;
import lv.mlproject17.CreditApp.database.model.Customer;
import lv.mlproject17.CreditApp.database.repository.CustomerRepository;
import lv.mlproject17.CreditApp.services.validators.RegisterCustomerValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;

/**
 * Created by martins.
 */

@RunWith(MockitoJUnitRunner.class)
public class RegisterCustomerServiceTest {
	@Mock
	private CustomerRepository repository;

	@InjectMocks
	private RegisterCustomerValidator validator = new RegisterCustomerValidator();

	@Test
	public void should_Return_Error_When_Email_Is_Null(){
		List<Error> errors = validator.validate(null, "password");
		assertThat(errors.size(), is(1));
		assertThat(errors.get(0).getField(), is("email"));
		assertThat(errors.get(0).getErrorMessage(), is("E-mail must be not empty"));
	}

	@Test
	public void should_Return_Error_When_Email_Is_Empty(){
		List<Error> errors = validator.validate("", "password");
		assertThat(errors.size(), is(1));
		assertThat(errors.get(0).getField(), is("email"));
		assertThat(errors.get(0).getErrorMessage(), is("E-mail must be not empty"));
	}

	@Test
	public void should_Return_Error_When_Email_Is_Incorrect(){
		List<Error> errors = validator.validate("martins", "password");
		assertThat(errors.size(), is(1));
		assertThat(errors.get(0).getField(), is("email"));
		assertThat(errors.get(0).getErrorMessage(), is("Incorrect e-mail"));
	}

	@Test
	public void should_Fail_If_User_With_Same_LoginEmail_Already_Exist_In_DB(){
		Customer customer = new Customer();
		doReturn(Optional.of(customer)).when(repository).findEmailByEmail("martins@email.lv");
		List<Error> errors = validator.validate("martins@email.lv", "password");
		assertThat(errors.size(), is(1));
		assertThat(errors.get(0).getField(), is("email"));
		assertThat(errors.get(0).getErrorMessage(), is("E-mail already exist"));
	}

//	@Test
//	public void should_Return_Error_When_Password_Is_Empty(){
//		List<Error> errors = validator.validate("martins@email.lv", "");
//		assertThat(errors.size(), is(1));
//		assertThat(errors.get(0).getField(), is("password"));
//		assertThat(errors.get(0).getErrorMessage(), is("Password must be not empty"));
//	}
//
//	@Test
//	public void should_Return_Error_When_Password_Is_Shorter_Than_Four_Symbols(){
//		List<Error> errors = validator.validate("martins@email.lv", "sd");
//		assertThat(errors.size(), is(1));
//		assertThat(errors.get(0).getField(), is("password"));
//		assertThat(errors.get(0).getErrorMessage(), is("Password must be not shorter than 4 symbols"));
//	}






}