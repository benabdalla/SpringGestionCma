package com.cma.gestion.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Value
@Entity
public class Commande {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idCommande;

	@ManyToOne
	private Agriculteur agriculteur;
	@ManyToOne
	private Produit produit;


	public Integer getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(Integer idCommande) {
		this.idCommande = idCommande;
	}

	public Agriculteur getAgriculteur() {
		return agriculteur;
	}

	public void setAgriculteur(Agriculteur agriculteur) {
		this.agriculteur = agriculteur;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}


}
