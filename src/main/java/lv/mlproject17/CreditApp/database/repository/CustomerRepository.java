package lv.mlproject17.CreditApp.database.repository;

import lv.mlproject17.CreditApp.database.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by marko on 2017.12.10..
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	@Query("SELECT id FROM Customer WHERE id.name = :name and id.password = :password")
	Long findByPasswordAndName(@Param("name") String name,
	                             @Param("password") String password);

//	@Query(SELECT id FROM Customer WHERE id.name = :name and id.password = :password")
//			findById(@Param("customer_id"))

	Customer save(Customer newCustomer);
}
