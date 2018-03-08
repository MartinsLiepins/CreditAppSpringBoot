package lv.mlproject17.CreditApp.services.validators;

import lv.mlproject17.CreditApp.api.Error;
import lv.mlproject17.CreditApp.database.repository.LoanRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by martins.
 */

@RunWith(MockitoJUnitRunner.class)
public class TakeLoanValidatorTest {
	@Mock
	private LoanRepository repo;
	@InjectMocks
	private TakeLoanValidator validator = new TakeLoanValidator();

	@Test
	public void should_Return_Error_When_Amount_Is_Zero(){
		List<Error> errors = validator.validate(new BigDecimal(0) , 10);
		assertThat(errors.size(), is(1));
		assertThat(errors.get(0).getField(), is("Loan Amount"));
		assertThat(errors.get(0).getErrorMessage(), is("Must not be zero value"));
	}

	@Test
	public void should_Return_Error_When_Amount_Is_Negative(){
		List<Error> errors = validator.validate(new BigDecimal(-10) , 10);
		assertThat(errors.size(), is(1));
		assertThat(errors.get(0).getField(), is("Loan Amount"));
		assertThat(errors.get(0).getErrorMessage(), is("Must not be negative"));
	}

	@Test
	public void should_Return_Error_When_Term_Is_Negative(){
		List<Error> errors = validator.validate(new BigDecimal(10) , -10);
		assertThat(errors.size(), is(1));
		assertThat(errors.get(0).getField(), is("Passing term"));
		assertThat(errors.get(0).getErrorMessage(), is("Must not be negative"));
	}

	@Test
	public void should_Return_Error_When_Term_Is_Zero(){
		List<Error> errors = validator.validate(new BigDecimal(10) , 0);
		assertThat(errors.size(), is(1));
		assertThat(errors.get(0).getField(), is("Passing term"));
		assertThat(errors.get(0).getErrorMessage(), is("Must not be zero"));
	}
}
