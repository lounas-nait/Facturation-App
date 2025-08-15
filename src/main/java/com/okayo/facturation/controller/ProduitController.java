package com.okayo.facturation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.okayo.facturation.classes.Categorie;
import com.okayo.facturation.classes.Produit;
import com.okayo.facturation.repository.ProduitRepository;
import com.okayo.facturation.repository.CategorieRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private CategorieRepository categorieRepository;

    @GetMapping
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createProduit(@RequestBody Produit produit) {

        Optional<Categorie> categorie = categorieRepository.findById(produit.getCategorie().getIdCategorie());
        if (categorie.isEmpty()) {
            return ResponseEntity.badRequest().body("Catégorie introuvable.");
        }

        produit.setCategorie(categorie.get());
        Produit savedProduit = produitRepository.save(produit);
        return ResponseEntity.ok(savedProduit);
    }

    // PUT : mise à jour produit (nom, prix, catégorie)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduit(@PathVariable Long id, @RequestBody Produit updatedProduit) {
        return produitRepository.findById(id)
                .map(produit -> {
                    produit.setNom(updatedProduit.getNom());
                    produit.setPrixHt(updatedProduit.getPrixHt());
                    produit.setCategorie(updatedProduit.getCategorie());

                    Produit saved = produitRepository.save(produit);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduit(@PathVariable Long id) {
        Optional<Produit> produit = produitRepository.findById(id);
        if (produit.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        produitRepository.delete(produit.get());
        return ResponseEntity.ok("Produit supprimé avec succès.");
    }

}
