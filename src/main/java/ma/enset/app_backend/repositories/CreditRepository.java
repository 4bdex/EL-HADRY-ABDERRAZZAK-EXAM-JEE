package ma.enset.app_backend.repositories;

import ma.enset.app_backend.entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit, Long> {
}
