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

    private static StockRepository stockRepository = null; // Injection d'une instance de StockRepository

    public StockService(StockRepository stockRepository) {
        StockService.stockRepository = stockRepository;
    }

    public static Stock getStockByProduitId(Long produitId) {
        if (produitId == null) {
            throw new IllegalArgumentException("Le produit ID ne peut pas être null.");
        }

        return stockRepository.findByProduitId(produitId)
                .orElseThrow(() -> new StockNotFoundException("Stock introuvable pour le produit ID : " + produitId));
    }
    public static Optional<Stock> findOptionalByProduitId(Long produitId) {
        return stockRepository.findByProduitId(produitId);
    }

    // Method to update stock after payment
    public void updateStock(Stock stock) {
        stockRepository.save(stock);
    }
    public void saveStock(Stock stock) {
        stockRepository.save(stock);
    }
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    // Other methods as necessary...
}
