package com.okayo.facturation.controller;

import com.okayo.facturation.classes.Facture;
import com.okayo.facturation.classes.LigneFacture;
import com.okayo.facturation.classes.Produit;
import com.okayo.facturation.classes.TauxTva;
import com.okayo.facturation.classes.Categorie;
import com.okayo.facturation.classes.Client;
import com.okayo.facturation.repository.FactureRepository;
import com.okayo.facturation.repository.ProduitRepository;
import com.okayo.facturation.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/factures")
public class FactureController {

        @Autowired
        private FactureRepository factureRepository;

        @Autowired
        private ClientRepository clientRepository;

        @Autowired
        private ProduitRepository produitRepository;

        @GetMapping
        public List<Facture> getAllFactures() {
                return factureRepository.findAll();
        }

        @PostMapping
        public Facture createFacture(@RequestBody Facture facture) {

                // Récupérer le client depuis la BD
                Client client = clientRepository.findById(facture.getClient().getIdClient())
                                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
                facture.setClient(client);

                double totalHt = 0;
                double totalTva = 0;

                // Date de facturation ou date du jour si null
                LocalDate dateFacture = facture.getDateFacturation() != null
                                ? facture.getDateFacturation()
                                : LocalDate.now();

                for (LigneFacture ligne : facture.getLignes()) {
                        Produit produit = produitRepository.findById(ligne.getProduit().getIdProduit())
                                        .orElseThrow(() -> new RuntimeException("Produit non trouvé"));

                        // Nom et prix du produit
                        ligne.setNomProduit(produit.getNom());
                        ligne.setPrixHt(produit.getPrixHt());

                        // Récupérer la catégorie du produit
                        Categorie categorie = produit.getCategorie();

                        // Trouver le taux TVA applicable à la date de facturation
                        TauxTva tauxApplicable = categorie.getTauxTVAList().stream()
                                        .filter(t -> (t.getDateDebut() == null
                                                        || !dateFacture.isBefore(t.getDateDebut())) &&
                                                        (t.getDateFin() == null
                                                                        || !dateFacture.isAfter(t.getDateFin())))
                                        .findFirst()
                                        .orElseThrow(() -> new RuntimeException(
                                                        "Aucun taux TVA valide trouvé pour la catégorie du produit "
                                                                        + produit.getNom()));

                        ligne.setTauxTva(tauxApplicable.getTaux());

                        // Calcul montant TTC ligne
                        double montantLigneHT = ligne.getPrixHt() * ligne.getQuantite();
                        double montantLigneTva = montantLigneHT * (ligne.getTauxTva() / 100);
                        double montantLigneTTC = montantLigneHT + montantLigneTva;

                        ligne.setMontantTTC(montantLigneTTC);

                        // totaux
                        totalHt += montantLigneHT;
                        totalTva += montantLigneTva;
                        ligne.setFacture(facture);
                }

                facture.setMontantHt(totalHt);
                facture.setMontantTva(totalTva);
                facture.setMontantTTC(totalHt + totalTva);

                return factureRepository.save(facture);
        }
}
