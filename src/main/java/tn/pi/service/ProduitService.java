package tn.pi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.pi.model.Produit;
import tn.pi.model.Stock;
import tn.pi.repository.ProduitRepository;
import tn.pi.repository.StockRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private StockRepository stockRepository;

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Optional<Produit> getProduitById(Long id) {
        return produitRepository.findById(id);
    }

    public Produit createProduit(Produit produit) {
        Produit savedProduit = produitRepository.save(produit);

        // Ajouter une ligne dans le stock avec quantité = 0
        Stock stock = new Stock();
        stock.setProduit(savedProduit);
        stock.setQuantite(0);
        stockRepository.save(stock);

        return savedProduit;
    }

    public Optional<Produit> updateProduit(Long id, Produit produit) {
        if (produitRepository.existsById(id)) {
            produit.setId(id);
            return Optional.of(produitRepository.save(produit));
        }
        return Optional.empty();
    }

    public List<Produit> getProduitsByIds(List<Long> ids) {
        return produitRepository.findAllById(ids);
    }

    public void deleteProduit(Long id) {
        Optional<Stock> stockOpt = stockRepository.findByProduitId(id);

        if (stockOpt.isPresent()) {
            Stock stock = stockOpt.get();
            if (stock.getQuantite() == 0) {
                // Supprimer le stock puis le produit
                stockRepository.delete(stock);
                produitRepository.deleteById(id);
            } else {
                throw new IllegalStateException("Impossible de supprimer le produit : la quantité en stock est supérieure à 0.");
            }
        } else {
            // Si aucun stock trouvé, on considère que c’est une incohérence, on peut aussi supprimer directement si tu veux
            produitRepository.deleteById(id);
        }
    }
}
