package tn.pi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.model.Approvisionnement;
import tn.pi.service.ApprovisionnementService;
import tn.pi.service.StockService;
import tn.pi.model.Stock;

@Controller
@RequestMapping("/approvisionnements")
public class ApprovisionnementController {

    @Autowired
    private ApprovisionnementService approvisionnementService;

    @Autowired
    private StockService stockService;

    // Method to handle stock increase (supply from supplier)
    @PostMapping("/increase/{produitId}")
    public String increaseStock(@PathVariable Long produitId, @RequestParam int quantity) {
        Stock stock = stockService.getStockByProduitId(produitId);
        if (stock != null) {
            stock.augmenter(quantity); // Increase the stock quantity
            stockService.saveStock(stock); // Save the updated stock information
        }
        return "redirect:/produits"; // Redirect back to the product list after the operation
    }

    // Method to handle the creation of an approvisionnement
    @PostMapping
    public String createApprovisionnement(@ModelAttribute Approvisionnement approvisionnement) {
        approvisionnementService.createApprovisionnement(approvisionnement);

        // Assuming the stock needs to be updated when the approvisionnement is created
        Long produitId = approvisionnement.getProduit().getId();
        int quantity = approvisionnement.getQuantite();
        Stock stock = stockService.getStockByProduitId(produitId);
        if (stock != null) {
            stock.augmenter(quantity); // Increase stock with the provided quantity
            stockService.saveStock(stock); // Save the updated stock
        }

        return "redirect:/approvisionnements"; // Redirect to the approvisionnements page
    }

    // Optional: Add a method to view all approvisionnements
    @GetMapping("/all")
    public String viewAllApprovisionnements(Model model) {
        model.addAttribute("approvisionnements", approvisionnementService.getAllApprovisionnements());
        return "approvisionnements/approvisionnements"; // A view to display all approvisionnements
    }
}
