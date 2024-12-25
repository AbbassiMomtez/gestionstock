package tn.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.model.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
}
