package lv.mlproject17.CreditApp.database.repository;

import lv.mlproject17.CreditApp.database.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by marko on 2017.12.10..
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

//	@Query("SELECT id FROM Customer c WHERE c.email = :email and c.password = :password")
	Optional<Customer> findCustomerByEmailAndPassword(@Param("email") String email,
	                                        @Param("password") String password);

//	@Query("SELECT email FROM Customer c WHERE c.email = :loginEmail")
	Optional<Customer> getByEmail(@Param("email") String email);

	Customer save(Customer newCustomer);
}
