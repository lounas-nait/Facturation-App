package com.okayo.facturation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.okayo.facturation.classes.LigneFacture;

public interface LigneFactureRepository extends JpaRepository<LigneFacture, Long> {
}