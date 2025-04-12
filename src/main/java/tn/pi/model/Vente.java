package tn.pi.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Vente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "vente", cascade = CascadeType.ALL)
    private List<LigneVente> lignes = new ArrayList<>(); // Initialisation de la liste

    private double prixTotalVente;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateVente;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<LigneVente> getLignes() {
        return lignes;
    }

    public void setLignes(List<LigneVente> lignes) {
        this.lignes = lignes;
    }

    public double getPrixTotalVente() {
        return prixTotalVente;
    }

    public void setPrixTotalVente(double prixTotalVente) {
        this.prixTotalVente = prixTotalVente;
    }

    public Date getDateVente() {
        return dateVente;
    }

    public void setDateVente(Date dateVente) {
        this.dateVente = dateVente;
    }
}
