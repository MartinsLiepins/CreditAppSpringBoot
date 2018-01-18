package lv.mlproject17.CreditApp.database.repository;

import lv.mlproject17.CreditApp.database.model.LoanApplication;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by marko on 2017.12.12..
 */
@Repository
public interface LoanApplicationRepository extends CrudRepository<LoanApplication, Long> {

	LoanApplication save(LoanApplication newApplication);

	@Modifying
	@Transactional
	@Query("UPDATE LoanApplication x SET x.applicationStatus = :status" +
			" WHERE x.applicationId = :id")
	int updateApplicationState(@Param("status") String Status,
							   @Param("id") Long applicationId);

//	@Modifying
//	@Transactional
//	@Query("UPDATE LoanApplication x SET x = :updateData" +
//			" WHERE x.applicationId = :id")
//	boolean updateApplicationState(@Param("updateData") LoanApplication application,
//	                           @Param("id") Long applicationId);

	@Query("SELECT MAX(id) FROM LoanApplication l WHERE l.customerId = :id")
	Optional<LoanApplication> getLastApplicationByCustomerId(@Param("id") Long customerId);

	@Query("SELECT MAX(id) FROM LoanApplication l WHERE l.customerId = :id")
	Long getLastApplicationIdByCustomerId(@Param("id") Long customerId);


}
