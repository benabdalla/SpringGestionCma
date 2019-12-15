package com.cma.gestion.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Value
@Entity
public class Agriculteur {
    @Id
    private Integer cin;
    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;
    @NotBlank
    private String adresse;
    @Min(8)
    private Integer tel;
    @NotBlank
    private String sexe;
    @OneToMany(mappedBy = "agriculteur")
    private Set<Commande> commande = new HashSet<>();

	

	public Integer getCin() {
        return cin;
    }

    public void setCin(Integer cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Set<Commande> getCommande() {
        return commande;
    }

    public void setCommande(Set<Commande> commande) {
        this.commande = commande;
    }

}
