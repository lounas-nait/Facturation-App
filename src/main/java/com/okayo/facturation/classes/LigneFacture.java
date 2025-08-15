package com.okayo.facturation.classes;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
public class LigneFacture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLigne;

    @ManyToOne
    @JoinColumn(name = "id_facture")

    private Facture facture;

    @ManyToOne
    @JoinColumn(name = "id_produit")
    @JsonBackReference
    private Produit produit;

    private String nomProduit;
    private Double prixHt;
    private Double tauxTva;
    private Integer quantite;
    private Double montantTTC;

    // Constructeurs
    public LigneFacture() {
    }

    public LigneFacture(Facture facture, String nomProduit, Double prixHt, Double tauxTva, Integer quantite,
            Double montantTTC) {
        this.facture = facture;
        this.nomProduit = nomProduit;
        this.prixHt = prixHt;
        this.tauxTva = tauxTva;
        this.quantite = quantite;
        this.montantTTC = montantTTC;
    }

    // Getters et Setters
    public Long getIdLigne() {
        return idLigne;
    }

    public void setIdLigne(Long idLigne) {
        this.idLigne = idLigne;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public Double getPrixHt() {
        return prixHt;
    }

    public void setPrixHt(Double prixHt) {
        this.prixHt = prixHt;
    }

    public Double getTauxTva() {
        return tauxTva;
    }

    public void setTauxTva(Double tauxTva) {
        this.tauxTva = tauxTva;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Double getMontantTTC() {
        return montantTTC;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setMontantTTC(Double montantTTC) {
        this.montantTTC = montantTTC;
    }
}