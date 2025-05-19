package elhadry.abderrazzak.bank_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import elhadry.abderrazzak.bank_backend.entities.Remboursement;

public interface RemboursementRepository extends JpaRepository<Remboursement, Long> {
}
