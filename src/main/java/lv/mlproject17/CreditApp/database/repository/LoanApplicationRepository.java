package lv.mlproject17.CreditApp.database.repository;

import lv.mlproject17.CreditApp.database.model.LoanApplication;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by marko on 2017.12.12..
 */
public interface LoanApplicationRepository extends CrudRepository<LoanApplication, Long> {
	LoanApplication save(LoanApplication newLoanRequest);
}
