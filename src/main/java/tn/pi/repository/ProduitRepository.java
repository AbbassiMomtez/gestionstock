package tn.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.model.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
