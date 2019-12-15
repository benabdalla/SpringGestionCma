package com.cma.gestion.repository;

import com.cma.gestion.entity.Commande;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    @Query("select  count(*) from Commande")
    public Long nbcommande();




}
