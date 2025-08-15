package com.okayo.facturation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.okayo.facturation.classes.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

}