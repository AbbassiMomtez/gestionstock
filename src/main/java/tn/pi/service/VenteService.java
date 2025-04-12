package tn.pi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.pi.model.Vente;
import tn.pi.repository.VenteRepository;

import java.util.List;

@Service
public class VenteService {

    @Autowired
    private VenteRepository venteRepository;

    // Sauvegarder un vente
    public void saveVente(Vente vente) {
        venteRepository.save(vente);
    }

    // Obtenir tous les ventes
    public List<Vente> getAllVentes() {
        return venteRepository.findAll();
    }
}
