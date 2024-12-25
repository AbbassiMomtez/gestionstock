package tn.pi.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "approvisionnements")
public class Approvisionnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fournisseur_id", nullable = true) // Nullable in case it's a sale
    private Fournisseur fournisseur;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    @Enumerated(EnumType.STRING)
    private TypeApprovisionnement type;

    private int quantite;

    private LocalDate dateApprovisionnement;

    public LocalDate getDateApprovisionnement() {
        return dateApprovisionnement;
    }

    public void setDateApprovisionnement(LocalDate dateApprovisionnement) {
        this.dateApprovisionnement = dateApprovisionnement;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public TypeApprovisionnement getType() {
        return type;
    }

    public void setType(TypeApprovisionnement type) {
        this.type = type;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Method to adjust stock based on the type of action
    public void ajusterStock(Stock stock) {
        if (this.type == TypeApprovisionnement.ACHAT) {
            stock.augmenter(quantite);
        } else if (this.type == TypeApprovisionnement.VENTE) {
            stock.diminuer(quantite);
        }
    }
}
