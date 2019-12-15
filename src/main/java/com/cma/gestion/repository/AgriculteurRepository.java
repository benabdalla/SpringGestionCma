package com.cma.gestion.repository;


import com.cma.gestion.data.Infodata;
import com.cma.gestion.entity.Agriculteur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface AgriculteurRepository extends JpaRepository<Agriculteur, Integer> {
    @Query("SELECT ag FROM Agriculteur ag where ag.nom like :x")
    public Page<Agriculteur> rechereche(@Param("x") String rech, Pageable pageable);

    @Query("SELECT count(cin) FROM Agriculteur ag")
    public Long NbAg();

    @Query(value="SELECT ag.cin,ag.nom,ag.prenom,p.id_produit,p.quntite,p.type,p.date FROM agriculteur ag INNER JOIN commande c ON ag.cin=c.agriculteur_cin INNER JOIN produit p ON c.produit_id_produit=p.id_produit",nativeQuery = true)
    public Set<Infodata> info();


}
