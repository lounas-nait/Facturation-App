package com.okayo.facturation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.okayo.facturation.classes.Facture;

public interface FactureRepository extends JpaRepository<Facture, Long> {

}