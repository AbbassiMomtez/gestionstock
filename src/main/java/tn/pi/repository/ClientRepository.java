package tn.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
