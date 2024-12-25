package tn.pi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.model.Stock;
import tn.pi.service.StockService;
import tn.pi.service.ProduitService;
import tn.pi.model.Produit;

@Controller
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private ProduitService produitService;

    // Method to display stock in a modal or page based on produitId
    @GetMapping("/{produitId}")
    public String getStockByProduitId(@PathVariable Long produitId, Model model) {
        Stock stock = stockService.getStockByProduitId(produitId);
        model.addAttribute("quantite", stock.getQuantite());
        return "stocks/stock_fragment"; // Renvoie un fragment Thymeleaf pour la quantité
    }


    // Method to increase stock when a supply is made (e.g., a supplier delivers products)
    @PostMapping("/increase/{produitId}")
    public String increaseStock(@PathVariable Long produitId, @RequestParam int quantity) {
        try {
            Stock stock = stockService.getStockByProduitId(produitId);
            if (stock != null) {
                stock.augmenter(quantity); // Increase the stock
                stockService.saveStock(stock); // Save the updated stock
            }
        } catch (Exception e) {
            // Handle exception if stock not found
            return "error/stock_not_found"; // Redirect to an error page if stock is not found
        }
        return "redirect:/produits"; // Redirect to the products list or wherever you need
    }

    // Method to decrease stock when a product is sold (e.g., a customer makes a purchase)
    @PostMapping("/decrease/{produitId}")
    public String decreaseStock(@PathVariable Long produitId, @RequestParam int quantity) {
        try {
            Stock stock = stockService.getStockByProduitId(produitId);
            if (stock != null && stock.getQuantite() >= quantity) {
                stock.diminuer(quantity); // Decrease the stock if there's enough quantity
                stockService.saveStock(stock); // Save the updated stock
            } else {
                // Handle insufficient stock situation (optional)
                return "error/insufficient_stock"; // Redirect to an error page if stock is insufficient
            }
        } catch (Exception e) {
            // Handle exception if stock not found
            return "error/stock_not_found"; // Redirect to an error page if stock is not found
        }
        return "redirect:/produits"; // Redirect to the products list or wherever you need
    }

    // Optional: You could add a page for viewing stock in a full list as well
    @GetMapping("/all")
    public String viewAllStocks(Model model) {
        model.addAttribute("stocks", stockService.getAllStocks());
        return "stocks/stock_list"; // This will render a list of all stocks
    }
}
