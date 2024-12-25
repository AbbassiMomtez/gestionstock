package tn.pi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.pi.model.Commande;
import tn.pi.repository.CommandeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public Optional<Commande> getCommandeById(Long id) {
        return commandeRepository.findById(id);
    }

    public Commande createCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    public Optional<Commande> updateCommande(Long id, Commande commande) {
        if (commandeRepository.existsById(id)) {
            commande.setId(id);
            return Optional.of(commandeRepository.save(commande));
        }
        return Optional.empty();
    }

    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }
}
