package tn.pi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.pi.model.Achat;
import tn.pi.repository.AchatRepository;

import java.util.List;

@Service
public class AchatService {

    @Autowired
    private AchatRepository achatRepository;

    // Sauvegarder un achat
    public void saveAchat(Achat achat) {
        achatRepository.save(achat);
    }

    // Obtenir tous les achats
    public List<Achat> getAllAchats() {
        return achatRepository.findAll();
    }
}
