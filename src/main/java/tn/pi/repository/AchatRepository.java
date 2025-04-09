package tn.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.model.Achat;

public interface AchatRepository extends JpaRepository<Achat, Long> {
}
