package lv.mlproject17.CreditApp.database.repository;

import lv.mlproject17.CreditApp.database.model.ExtendedLoans;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by marko on 2017.12.10..
 */
@Repository
public interface ExtendedLoanRepository extends CrudRepository<ExtendedLoans, Long> {

	ExtendedLoans save(ExtendedLoans newExtendedLoan);

//	@Query("SELECT extendedAmount FROM ExtendedLoans l WHERE l.loanId = :loanId and l.id = max(id)")
	@Query("SELECT extendedAmount FROM ExtendedLoans l WHERE l.loanId = :loanId")
	BigDecimal findExtendedLoanAmount(@Param("loanId") Long loanId);

	@Query("SELECT l FROM ExtendedLoans l WHERE l.loanId = :id")
	List<ExtendedLoans> findAllUserExtendedLoans(@Param("id") Long loanId);

}
