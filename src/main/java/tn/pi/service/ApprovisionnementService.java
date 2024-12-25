package tn.pi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.pi.model.Approvisionnement;
import tn.pi.repository.ApprovisionnementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ApprovisionnementService {

    @Autowired
    private ApprovisionnementRepository approvisionnementRepository;

    public List<Approvisionnement> getAllApprovisionnements() {
        return approvisionnementRepository.findAll();
    }

    public Optional<Approvisionnement> getApprovisionnementById(Long id) {
        return approvisionnementRepository.findById(id);
    }

    public Approvisionnement createApprovisionnement(Approvisionnement approvisionnement) {
        return approvisionnementRepository.save(approvisionnement);
    }

    public Optional<Approvisionnement> updateApprovisionnement(Long id, Approvisionnement approvisionnement) {
        if (approvisionnementRepository.existsById(id)) {
            approvisionnement.setId(id);
            return Optional.of(approvisionnementRepository.save(approvisionnement));
        }
        return Optional.empty();
    }

    public void deleteApprovisionnement(Long id) {
        approvisionnementRepository.deleteById(id);
    }
}
