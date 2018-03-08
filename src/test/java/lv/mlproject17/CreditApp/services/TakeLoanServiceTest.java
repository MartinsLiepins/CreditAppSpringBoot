package lv.mlproject17.CreditApp.services;

/**
 * Created by martins.
 */

//@RunWith(MockitoJUnitRunner.class)
//public class TakeLoanServiceTest {
//	@Mock
//	private LoanRepository repo;
//	@Mock
//	private ApplicationRepository applicationRepo;
//	@Mock
//	private TakeLoanValidator validator = new TakeLoanValidator();
//
//	@Test
//	public void should_Return_Error_When_First_Application_Is_Greater_Than_400(){
//		Loan loan = new Loan();
//
//		doReturn(Optional.empty()).when(applicationRepo).findFirstByCustomerIdOrderByIdDesc(new Long(1));
//
//
//
//		TakeLoanService takeLoanService = mock(TakeLoanService.class);
//		List<Warning> warning = when(takeLoanService.takeLoan(new BigDecimal(500), 3)).thenReturn();
//		assertThat(warning.size(), is(1));
//		assertThat(warning.get(0).getWarningMessage(), is("Must not be zero"));

//		doReturn(Optional.of(loan)).when(repo).findFirstByCustomerIdOrderByIdDesc(new Long(1));



//	}



//}