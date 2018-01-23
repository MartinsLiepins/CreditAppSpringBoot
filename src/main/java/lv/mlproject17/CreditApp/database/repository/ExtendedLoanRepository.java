package lv.mlproject17.CreditApp.database.repository;

import lv.mlproject17.CreditApp.database.model.ExtendedLoans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by marko on 2017.12.10..
 */
@Repository
public interface ExtendedLoanRepository extends CrudRepository<ExtendedLoans, Long> {

	ExtendedLoans save(ExtendedLoans newExtendedLoan);

//	@Query("SELECT extendedAmount FROM ExtendedLoans l WHERE l.loanId = :loanId")
	ExtendedLoans findFirstByLoanIdOrderByIdDesc(Long loanId);

//	@Query("SELECT l FROM ExtendedLoans l WHERE l.loanId = :id")
	List<ExtendedLoans> findExtendedLoansByLoanId(Long loanId);
}
