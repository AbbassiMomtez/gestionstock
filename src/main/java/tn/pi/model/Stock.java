package tn.pi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "produit_id",referencedColumnName = "id", nullable = false)
    private Produit produit;

    private int quantite = 0;

    // Getter pour quantite
    public int getQuantite() {
        return quantite;
    }

    // Setter pour quantite
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    // Méthode pour augmenter la quantité en stock
    public void augmenter(int quantite) {
        this.quantite += quantite;
    }

    // Méthode pour diminuer la quantité en stock
    public void diminuer(int quantite) {
        this.quantite -= quantite;
    }

    // Getters et setters pour autres propriétés
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
