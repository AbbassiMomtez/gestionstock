package tn.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.model.Paiement;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
}
