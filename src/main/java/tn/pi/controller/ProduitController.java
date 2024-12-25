package tn.pi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.model.Produit;
import tn.pi.service.ProduitService;
import tn.pi.model.Stock;
import tn.pi.service.StockService;

import java.util.List;

@Controller
@RequestMapping("/produits")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @GetMapping
    public String getAllProduits(Model model) {
        List<Produit> produits = produitService.getAllProduits();

        // Inject stock information into each product
        for (Produit produit : produits) {
            Stock stock = StockService.getStockByProduitId(produit.getId());
            produit.setQuantiteStock(stock != null ? stock.getQuantite() : 0); // Set the quantity stock or 0 if null
        }

        model.addAttribute("produits", produits);
        return "produits/produit"; // Retourne la vue des produits
    }

    @GetMapping("/add")
    public String addProduitForm(Model model) {
        model.addAttribute("produit", new Produit());
        return "produits/add_produit"; // Returns the view to add a new product
    }

    @PostMapping
    public String createProduit(@ModelAttribute Produit produit) {
        produitService.createProduit(produit);
        return "redirect:/produits"; // Redirects to the list of products after adding
    }

    @GetMapping("/edit/{id}")
    public String editProduitForm(@PathVariable Long id, Model model) {
        Produit produit = produitService.getProduitById(id).orElse(null);
        if (produit != null) {
            model.addAttribute("produit", produit);
            return "produits/edit_produit"; // Returns the view to edit a product
        }
        return "redirect:/produits"; // Redirects to product list if product is not found
    }

    @PostMapping("/edit/{id}")
    public String updateProduit(@PathVariable Long id, @ModelAttribute Produit produit) {
        produitService.updateProduit(id, produit);
        return "redirect:/produits"; // Redirects to the list of products after editing
    }

    @GetMapping("/delete/{id}")
    public String deleteProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
        return "redirect:/produits"; // Redirects to the list of products after deletion
    }
}
