package com.okayo.facturation.classes;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "factures")
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateFacturation;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idClient")
    private Client client;

    @Column(name = "montant_ht", nullable = false)
    private Double montantHt;

    @Column(name = "montant_tva", nullable = false)
    private Double montantTva;

    @Column(name = "montant_ttc", nullable = false)
    private Double montantTTC;

    @OneToMany(mappedBy = "facture", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<LigneFacture> lignes = new ArrayList<>();

    public Facture() {
    }

    public Facture(Client client, LocalDate dateFacturation) {
        this.client = client;
        this.dateFacturation = dateFacturation;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDateFacturation() {
        return dateFacturation;
    }

    public void setDateFacturation(LocalDate dateFacturation) {
        this.dateFacturation = dateFacturation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setMontantHt(double montantHt) {
        this.montantHt = montantHt;
    }

    public void setMontantTva(double montantTva) {
        this.montantTva = montantTva;
    }

    public void setMontantTTC(double montantTTC) {
        this.montantTTC = montantTTC;
    }

    public double getMontantHt() {
        return montantHt;
    }

    public double getMontantTva() {
        return montantTva;
    }

    public double getMontantTTC() {
        return montantTTC;
    }

    public List<LigneFacture> getLignes() {
        return lignes;
    }

    public void setLignes(List<LigneFacture> lignes) {
        this.lignes = lignes;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
