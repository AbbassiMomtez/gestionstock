package tn.pi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.pi.model.Paiement;
import tn.pi.repository.PaiementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PaiementService {

    @Autowired
    private PaiementRepository paiementRepository;

    // Create a new paiement
    public Paiement createPaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    // Get paiement by ID
    public Optional<Paiement> getPaiementById(Long id) {
        return paiementRepository.findById(id);
    }

    // Get all paiements
    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }

    // Delete paiement by ID
    public void deletePaiement(Long id) {
        paiementRepository.deleteById(id);
    }

    // Other methods as necessary...
}
