package tn.pi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.model.Paiement;
import tn.pi.model.Stock;
import tn.pi.service.PaiementService;
import tn.pi.service.StockService;

import java.util.List;

@Controller
@RequestMapping("/paiements")
public class PaiementController {

    @Autowired
    private PaiementService paiementService;

    @Autowired
    private StockService stockService;

    @GetMapping
    public String getAllPaiements(Model model) {
        List<Paiement> paiements = paiementService.getAllPaiements();
        model.addAttribute("paiements", paiements);
        return "paiements/paiements"; // Return the view for displaying the payments
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paiement> getPaiementById(@PathVariable Long id) {
        return paiementService.getPaiementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public String createPaiement(@ModelAttribute Paiement paiement) {
        Paiement createdPaiement = paiementService.createPaiement(paiement);

        // Update stock when a client makes a purchase
        Long produitId = paiement.getProduit().getId(); // Access the associated product
        int quantity = paiement.getQuantite();
        Stock stock = stockService.getStockByProduitId(produitId);

        if (stock != null && stock.getQuantite() >= quantity) {
            stock.diminuer(quantity); // Decrease the stock based on the purchase quantity
            stockService.updateStock(stock); // Assuming stockService handles stock updates
        } else {
            // Handle case where there is not enough stock
            return "redirect:/paiements?error=Not+enough+stock+available"; // Error redirect
        }

        return "redirect:/paiements"; // Redirect to the list of payments after creation
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaiement(@PathVariable Long id) {
        paiementService.deletePaiement(id);
        return ResponseEntity.noContent().build();
    }
}
