package tn.pi.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Achat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Fournisseur fournisseur;

    @OneToMany(mappedBy = "achat", cascade = CascadeType.ALL)
    private List<LigneAchat> lignes = new ArrayList<>(); // Initialisation de la liste

    private double prixTotalAchat;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAchat;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public List<LigneAchat> getLignes() {
        return lignes;
    }

    public void setLignes(List<LigneAchat> lignes) {
        this.lignes = lignes;
    }

    public double getPrixTotalAchat() {
        return prixTotalAchat;
    }

    public void setPrixTotalAchat(double prixTotalAchat) {
        this.prixTotalAchat = prixTotalAchat;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }
}
