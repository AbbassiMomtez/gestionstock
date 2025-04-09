package tn.pi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.model.*;
import tn.pi.repository.*;
import tn.pi.service.AchatService;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/achats")

public class AchatController {

    @Autowired
    private AchatService achatService;

    @GetMapping
    public String getAllAchats(Model model) {
        List<Achat> achats = achatService.getAllAchats();
        model.addAttribute("achats", achats);
        return "achats/liste_achats"; // Vue pour afficher les achats
    }
    @Autowired
    private AchatRepository achatRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private LigneAchatRepository ligneAchatRepository;

    // Afficher le formulaire d'ajout d'achat
    @GetMapping("/add")
    public String afficherFormulaireAjout(Model model) {
        List<Produit> produits = produitRepository.findAll();
        List<Fournisseur> fournisseurs = fournisseurRepository.findAll();
        model.addAttribute("produits", produits);
        model.addAttribute("fournisseurs", fournisseurs);
        return "achats/ajouter_achat"; // Le nom du template Thymeleaf
    }

    // Ajouter un achat
    @PostMapping("/add")
    public String ajouterAchat(@RequestParam Long fournisseurId, @RequestParam List<Long> produitIds,
                               @RequestParam List<Integer> quantites, Model model) {
        // Créer un nouvel achat
        Achat achat = new Achat();
        achat.setFournisseur(fournisseurRepository.findById(fournisseurId).orElse(null));
        achat.setDateAchat(new Date());

        double prixTotalAchat = 0;

        // Créer une ligne d'achat pour chaque produit sélectionné
        for (int i = 0; i < produitIds.size(); i++) {
            Produit produit = produitRepository.findById(produitIds.get(i)).orElse(null);
            int quantite = quantites.get(i);

            // Calculer le prix total pour cette ligne d'achat
            double prixUnitaire = produit.getPrix();
            double prixTotal = prixUnitaire * quantite * 0.81; // Appliquer la réduction de 19%

            // Créer et ajouter la ligne d'achat
            LigneAchat ligneAchat = new LigneAchat();
            ligneAchat.setProduit(produit);
            ligneAchat.setQuantite(quantite);
            ligneAchat.setPrixUnitaire(prixUnitaire);
            ligneAchat.setAchat(achat);

            // Ajouter la ligne d'achat à l'achat
            achat.getLignes().add(ligneAchat); // Ajoute la ligne à la liste

            // Ajouter le prix total à la somme globale
            prixTotalAchat += prixTotal;
        }

        // Définir le prix total de l'achat
        achat.setPrixTotalAchat(prixTotalAchat);

        // Sauvegarder l'achat et ses lignes
        achatRepository.save(achat);

        // Mettre à jour les stocks
        for (int i = 0; i < produitIds.size(); i++) {
            Produit produit = produitRepository.findById(produitIds.get(i)).orElse(null);
            Stock stock = stockRepository.findByProduitId(produit.getId()).orElse(null);
            if (stock != null) {
                stock.augmenter(quantites.get(i)); // Augmenter la quantité en stock
                stockRepository.save(stock);
            }
        }

        return "redirect:/achats"; // Rediriger vers la page des achats après l'ajout
    }


}
