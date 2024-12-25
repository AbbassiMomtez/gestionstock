package tn.pi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    private int quantite;

    // Getter for quantite
    public int getQuantite() {
        return quantite;
    }

    // Setter for quantite
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    // Method to increase stock
    public void augmenter(int quantite) {
        this.quantite += quantite;
    }

    // Method to decrease stock
    public void diminuer(int quantite) {
        this.quantite -= quantite;
    }

    // Getters and setters for other properties
}
