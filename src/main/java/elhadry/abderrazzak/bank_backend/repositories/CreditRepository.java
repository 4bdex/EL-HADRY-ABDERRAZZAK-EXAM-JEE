package elhadry.abderrazzak.bank_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import elhadry.abderrazzak.bank_backend.entities.Credit;

public interface CreditRepository extends JpaRepository<Credit, Long> {
}
