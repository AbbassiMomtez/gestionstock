package tn.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.model.Vente;

public interface VenteRepository extends JpaRepository<Vente, Long> {
}
