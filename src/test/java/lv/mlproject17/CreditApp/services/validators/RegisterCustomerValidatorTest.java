package lv.mlproject17.CreditApp.services.validators;

import lv.mlproject17.CreditApp.api.Error;
import lv.mlproject17.CreditApp.database.model.Customer;
import lv.mlproject17.CreditApp.database.repository.CustomerRepository;
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
public class RegisterCustomerValidatorTest {

	@Mock
	private CustomerRepository repository;

	@InjectMocks
	private RegisterCustomerValidator validator = new RegisterCustomerValidator();

	@Test
	public void should_Return_Error_When_Email_Is_Null(){
		List<Error> errors = validator.validate(null, "password");
		assertThat(errors.size(), is(1));
		assertThat(errors.get(0).getField(), is("loginEmail"));
		assertThat(errors.get(0).getErrorMessage(), is("E-mail must not be empty"));
	}

	@Test
	public void should_Return_Error_When_Email_Is_Empty(){
		List<Error> errors = validator.validate("", "password");
		assertThat(errors.size(), is(1));
		assertThat(errors.get(0).getField(), is("loginEmail"));
		assertThat(errors.get(0).getErrorMessage(), is("E-mail must not be empty"));
	}

	@Test
	public void should_Return_Error_When_Email_Is_Incorrect(){
		List<Error> errors = validator.validate("martins", "password");
		assertThat(errors.size(), is(1));
		assertThat(errors.get(0).getField(), is("loginEmail"));
		assertThat(errors.get(0).getErrorMessage(), is("Incorrect e-mail"));
	}

	@Test
	public void should_Fail_If_User_With_Same_Email_Already_Exist_In_DB(){
		Customer customer = new Customer();
		doReturn(Optional.of(customer)).when(repository).getByEmail("martins@email.lv");
		List<Error> errors = validator.validate("martins@email.lv", "password");
		assertThat(errors.size(), is(1));
		assertThat(errors.get(0).getField(), is("loginEmail"));
		assertThat(errors.get(0).getErrorMessage(), is("E-mail already exist"));
	}

	@Test
	public void should_Return_Error_When_Password_Is_Empty(){
		Customer customer = new Customer();
		doReturn(Optional.of(customer)).when(repository).getByEmail("martins@email.lv");

		List<Error> errors = validator.validate("martins@email.lv", "");
		assertThat(errors.size(), is(2));
		assertThat(errors.get(1).getField(), is("password"));
		assertThat(errors.get(1).getErrorMessage(), is("Password must not be empty"));
	}

	@Test
	public void should_Return_Error_When_Password_Is_Shorter_Than_Four_Symbols(){
		Customer customer = new Customer();
		doReturn(Optional.of(customer)).when(repository).getByEmail("martins@email.lv");
		List<Error> errors = validator.validate("martins@email.lv", "sd");
		assertThat(errors.size(), is(2));
		assertThat(errors.get(1).getField(), is("password"));
		assertThat(errors.get(1).getErrorMessage(), is("Password must not be shorter than 4 symbols"));
	}
}