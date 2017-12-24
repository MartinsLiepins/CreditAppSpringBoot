package lv.mlproject17.CreditApp.database.repository;

import lv.mlproject17.CreditApp.database.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by marko on 2017.12.10..
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	@Query("SELECT id FROM Customer c WHERE c.name = :name and c.password = :password")
	Optional<Long> findIdByPasswordAndName(@Param("name") String name,
	                                       @Param("password") String password);

	@Query("SELECT id FROM Customer c WHERE c.name = :name and c.password = :password")
	Long getIdByPasswordAndName(@Param("name") String name,
	                                     @Param("password") String password);

	@Query("SELECT name FROM Customer c WHERE c.name = :loginName")
	Optional<String> findNameByLoginName(@Param("loginName") String loginName);

	Customer save(Customer newCustomer);
}
