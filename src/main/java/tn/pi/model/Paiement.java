package tn.pi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "paiements")
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    private int quantite;

    // Getter for produit
    public Produit getProduit() {
        return produit;
    }

    // Setter for produit
    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    // Getter for quantite
    public int getQuantite() {
        return quantite;
    }

    // Setter for quantite
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    // Other necessary fields and methods...
}
