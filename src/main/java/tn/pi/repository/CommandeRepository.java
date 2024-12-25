package tn.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.model.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
