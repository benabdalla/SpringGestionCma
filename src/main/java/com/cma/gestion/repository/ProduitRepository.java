package com.cma.gestion.repository;

import com.cma.gestion.entity.Commande;
import com.cma.gestion.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit,Integer> {
    @Query("select sum(p.quntite) from  Produit p ")
    public  int  totaleProduit();
    @Query("select count(p.quntite) from  Produit p ")
    public  int  nbProduit();
    @Query("select min(p.quntite) from  Produit p ")
    public  int  minProduit();
    @Query("select max(p.quntite) from  Produit p ")
    public  int  maxProduit();



}
