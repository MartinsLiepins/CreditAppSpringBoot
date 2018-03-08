package lv.mlproject17.CreditApp.services.threads;

/**
 * Created by martins.
 */

//@RunWith(MockitoJUnitRunner.class)
//public class RiskAnalysis {
//	@Mock
//	private LoanRepository loanRepoMock;
//	@Mock
//	private ApplicationRepository applicationRepoInMock;
//	@Mock
//	ApplicationBuilder builderInMock;
//	@Mock
//	Application applicationInMock = mock(Application.class);
//	@InjectMocks
//	RiskAnalysis riskAnalysisMock = new RiskAnalysis();
//
//	@Test
//	public void should_Return_Error_When_First_Application_Is_Greater_Than_400(){
//
//		applicationInMock = createApplication()
//				.withCustomerId(new Long(1))
//				.withApplicationAmount(new BigDecimal(500))
//				.withPassingTermDays(10)
//				.withDate(LocalDateTime.now())
//				.build();
//
//		doReturn(Optional.empty()).when(loanRepoMock).findFirstByCustomerIdOrderByIdDesc(new Long(1));
//
//		Application application =
//		List<Warning> warning = when(takeLoanService.takeLoan(new BigDecimal(500), 3)).thenReturn();
//		assertThat(warning.size(), is(1));
//		assertThat(warning.get(0).getWarningMessage(), is("Must not be zero"));
//	}
//}
