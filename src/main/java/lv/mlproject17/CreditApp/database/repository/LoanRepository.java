package lv.mlproject17.CreditApp.database.repository;

import lv.mlproject17.CreditApp.database.model.Loan;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by marko on 2017.12.10..
 */
public interface LoanRepository extends CrudRepository<Loan, Long> {
	Loan save(Loan newLoan);
}
