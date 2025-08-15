package com.okayo.facturation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.okayo.facturation.classes.TauxTva;

@Repository
public interface TauxTvaRepository extends JpaRepository<TauxTva, Long> {

}
