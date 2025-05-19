package elhadry.abderrazzak.bank_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import elhadry.abderrazzak.bank_backend.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
