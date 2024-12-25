package tn.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.model.Approvisionnement;

public interface ApprovisionnementRepository extends JpaRepository<Approvisionnement, Long> {
}
