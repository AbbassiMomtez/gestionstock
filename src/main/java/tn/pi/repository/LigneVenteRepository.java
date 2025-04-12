package tn.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.model.LigneVente;

public interface LigneVenteRepository extends JpaRepository<LigneVente, Long> {
}
