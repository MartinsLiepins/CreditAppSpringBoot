package lv.mlproject17.CreditApp.database.repository;

import lv.mlproject17.CreditApp.database.model.Loan;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by marko on 2017.12.10..
 */

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {

	Loan save(Loan loan);

//	@Query("SELECT l FROM Loan l WHERE l.customerId = :id")
	Optional<List<Loan>> findLoanByCustomerId(Long customerId);

//	Optional<Loan> findFirstByCustomerIdOrderByIdDesc(@Param("id") Long customerId);
	Optional<Loan> findFirstByCustomerIdOrderByIdDesc(Long customerId);

	@Modifying
	@Transactional
	@Query("UPDATE Loan x SET x.loanRepayState = :status" +
			" WHERE x.id = :id")
	int updateLoanRepayState(@Param("status") boolean status,
	                         @Param("id") Long id);

	@Modifying
	@Transactional
	@Query("UPDATE Loan x SET x.loanExtended = :status" +
			" WHERE x.id = :id")
	int updateLoanExtendedState(@Param("status") boolean status,
	                            @Param("id") Long id);
}
