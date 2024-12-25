package tn.pi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.pi.model.Fournisseur;
import tn.pi.repository.FournisseurRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FournisseurService {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurRepository.findAll();
    }

    public Optional<Fournisseur> getFournisseurById(Long id) {
        return fournisseurRepository.findById(id);
    }

    public Fournisseur createFournisseur(Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }

    public Optional<Fournisseur> updateFournisseur(Long id, Fournisseur fournisseur) {
        if (fournisseurRepository.existsById(id)) {
            fournisseur.setId(id);
            return Optional.of(fournisseurRepository.save(fournisseur));
        }
        return Optional.empty();
    }

    public void deleteFournisseur(Long id) {
        fournisseurRepository.deleteById(id);
    }
}
