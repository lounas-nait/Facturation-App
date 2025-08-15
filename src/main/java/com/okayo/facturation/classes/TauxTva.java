package com.okayo.facturation.classes;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class TauxTva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTva;

    private double taux;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    @ManyToOne
    @JoinColumn(name = "idCategorie")
    @JsonBackReference
    private Categorie categorie;

    public TauxTva() {
    }

    public TauxTva(double taux, LocalDate dateDebut, LocalDate dateFin, Categorie categorie) {
        this.taux = taux;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.categorie = categorie;
    }

    public Long getIdTva() {
        return idTva;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public void setIdTva(Long idTva) {
        this.idTva = idTva;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Categorie getCategorie() {
        return categorie;
    }

}