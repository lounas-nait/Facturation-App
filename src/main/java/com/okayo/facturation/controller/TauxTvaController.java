package com.okayo.facturation.controller;

import org.springframework.web.bind.annotation.*;

import com.okayo.facturation.classes.Categorie;
import com.okayo.facturation.classes.TauxTva;
import com.okayo.facturation.repository.CategorieRepository;
import com.okayo.facturation.repository.TauxTvaRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tva")
public class TauxTvaController {

    private final TauxTvaRepository tauxTvaRepository;
    private final CategorieRepository categorieRepository;

    public TauxTvaController(TauxTvaRepository tauxTvaRepository, CategorieRepository categorieRepository) {
        this.tauxTvaRepository = tauxTvaRepository;
        this.categorieRepository = categorieRepository;
    }

    @GetMapping
    public List<TauxTva> getAllTaux() {
        return tauxTvaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createTaux(@RequestBody TauxTva taux) {
        if (taux.getCategorie() == null || taux.getCategorie().getIdCategorie() == null) {
            return ResponseEntity.badRequest().body("catégorie obligatoire.");
        }

        Optional<Categorie> categorie = categorieRepository.findById(taux.getCategorie().getIdCategorie());
        if (categorie.isEmpty()) {
            return ResponseEntity.badRequest().body("Catégorie introuvable.");
        }

        taux.setCategorie(categorie.get());
        TauxTva savedTaux = tauxTvaRepository.save(taux);
        return ResponseEntity.ok(savedTaux);
    }

    // PUT : modification taux existant
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTaux(@PathVariable Long id, @RequestBody TauxTva updatedTaux) {
        return tauxTvaRepository.findById(id)
                .map(taux -> {
                    taux.setTaux(updatedTaux.getTaux());
                    taux.setDateDebut(updatedTaux.getDateDebut());
                    taux.setDateFin(updatedTaux.getDateFin());

                    if (updatedTaux.getCategorie() != null && updatedTaux.getCategorie().getIdCategorie() != null) {
                        categorieRepository.findById(updatedTaux.getCategorie().getIdCategorie())
                                .ifPresent(taux::setCategorie);
                    }

                    TauxTva saved = tauxTvaRepository.save(taux);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTauxTVA(@PathVariable Long id) {
        Optional<TauxTva> tauxTVAOpt = tauxTvaRepository.findById(id);
        if (tauxTVAOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TauxTva introuvable.");
        }

        tauxTvaRepository.deleteById(id);
        return ResponseEntity.ok("TauxTva supprimé avec succès.");
    }

}
