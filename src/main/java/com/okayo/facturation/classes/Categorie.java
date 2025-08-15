package com.okayo.facturation.classes;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategorie;

    private String nom;

    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Produit> produits;

    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TauxTva> tauxTVAList;

    public Categorie() {
    }

    public Categorie(String nom) {
        this.nom = nom;
    }

    public Long getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Long idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public List<TauxTva> getTauxTVAList() {
        return tauxTVAList;
    }

    public void setTauxTVAList(List<TauxTva> tauxTVAList) {
        this.tauxTVAList = tauxTVAList;
    }
}