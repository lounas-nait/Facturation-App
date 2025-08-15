package com.okayo.facturation.controller;

import com.okayo.facturation.classes.Categorie;
import com.okayo.facturation.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {

    @Autowired
    private CategorieRepository categorieRepository;

    @PostMapping
    public Categorie addCategorie(@RequestBody Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @GetMapping
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }
}
