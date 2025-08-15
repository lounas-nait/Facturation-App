package com.okayo.facturation.controller;

import com.okayo.facturation.classes.LigneFacture;
import com.okayo.facturation.repository.LigneFactureRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lignesfacture")
public class LigneFactureController {

    private final LigneFactureRepository ligneFactureRepository;

    public LigneFactureController(LigneFactureRepository ligneFactureRepository) {
        this.ligneFactureRepository = ligneFactureRepository;
    }

    @GetMapping
    public List<LigneFacture> getAllLignes() {
        return ligneFactureRepository.findAll();
    }

}
