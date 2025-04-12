package tn.pi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.model.*;
import tn.pi.repository.*;
import tn.pi.service.VenteService;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/ventes")

public class VenteController {

    @Autowired
    private VenteService venteService;

    @GetMapping
    public String getAllVentes(Model model) {
        List<Vente> ventes = venteService.getAllVentes();
        model.addAttribute("ventes", ventes);
        return "ventes/liste_ventes"; // Vue pour afficher les ventes
    }
    @Autowired
    private VenteRepository venteRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private LigneVenteRepository ligneVenteRepository;

    // Afficher le formulaire d'ajout d'vente
    @GetMapping("/add")
    public String afficherFormulaireAjout(Model model) {
        List<Produit> produits = produitRepository.findAll();
        List<Client> clients = clientRepository.findAll();
        model.addAttribute("produits", produits);
        model.addAttribute("clients", clients);
        return "ventes/ajouter_vente"; // Le nom du template Thymeleaf
    }

    // Ajouter un vente
    @PostMapping("/add")
    public String ajouterVente(@RequestParam Long clientId, @RequestParam List<Long> produitIds,
                               @RequestParam List<Integer> quantites, Model model) {
        // Créer un nouvel vente
        Vente vente = new Vente();
        vente.setClient(clientRepository.findById(clientId).orElse(null));
        vente.setDateVente(new Date());

        double prixTotalVente = 0;

        // Créer une ligne d'vente pour chaque produit sélectionné
        for (int i = 0; i < produitIds.size(); i++) {
            Produit produit = produitRepository.findById(produitIds.get(i)).orElse(null);
            int quantite = quantites.get(i);

            // Calculer le prix total pour cette ligne d'vente
            double prixUnitaire = produit.getPrix();
            double prixTotal = prixUnitaire * quantite; // Appliquer la réduction de 19%

            // Créer et ajouter la ligne d'vente
            LigneVente ligneVente = new LigneVente();
            ligneVente.setProduit(produit);
            ligneVente.setQuantite(quantite);
            ligneVente.setPrixUnitaire(prixUnitaire);
            ligneVente.setVente(vente);

            // Ajouter la ligne d'vente à l'vente
            vente.getLignes().add(ligneVente); // Ajoute la ligne à la liste

            // Ajouter le prix total à la somme globale
            prixTotalVente += prixTotal;
        }

        // Définir le prix total de l'vente
        vente.setPrixTotalVente(prixTotalVente);

        // Sauvegarder l'vente et ses lignes
        venteRepository.save(vente);

        // Mettre à jour les stocks
        for (int i = 0; i < produitIds.size(); i++) {
            Produit produit = produitRepository.findById(produitIds.get(i)).orElse(null);
            Stock stock = stockRepository.findByProduitId(produit.getId()).orElse(null);
            if (stock != null) {
                stock.diminuer(quantites.get(i)); // Augmenter la quantité en stock
                stockRepository.save(stock);
            }
        }

        return "redirect:/ventes"; // Rediriger vers la page des ventes après l'ajout
    }


}
