package lv.mlproject17.CreditApp.database.repository;

import lv.mlproject17.CreditApp.database.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

	Application save(Application newApplication);

	Optional<Application> findFirstByCustomerIdOrderByIdDesc(Long customerId);
}
