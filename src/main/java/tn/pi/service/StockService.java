package tn.pi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.pi.model.Stock;
import tn.pi.repository.StockRepository;
import tn.pi.exception.StockNotFoundException;
import java.util.Optional;
import java.util.List;


@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository; // Injection d'une instance de StockRepository

    public Stock getStockByProduitId(Long produitId) {
        if (produitId == null) {
            throw new IllegalArgumentException("Le produit ID ne peut pas être null.");
        }

        // Appel correct à la méthode non statique findByProduitId
        Optional<Stock> stockOptional = stockRepository.findByProduitId(produitId);
        if (stockOptional == null) {
            throw new IllegalStateException("La méthode findByProduitId retourne null. Vérifiez StockRepository.");
        }

        return stockOptional.orElseThrow(() -> new StockNotFoundException("Stock introuvable pour le produit ID : " + produitId));
    }

    // Method to update stock after payment
    public void updateStock(Stock stock) {
        stockRepository.save(stock);
    }
    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    // Other methods as necessary...
}
