package lv.mlproject17.CreditApp.database.repository;

import lv.mlproject17.CreditApp.database.model.ExtendedLoans;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by marko on 2017.12.10..
 */
public interface ExtendedLoanRepository extends CrudRepository<ExtendedLoans, Long> {
}
