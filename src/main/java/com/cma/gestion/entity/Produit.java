
package com.cma.gestion.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Value
@Entity
@Table(name = "Produit")
public class Produit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer idProduit;
    private String type;
    private Float quntite;
    private Float prix;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @OneToMany(mappedBy = "produit")
    private Set<Commande> commande = new HashSet<>();


    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getQuntite() {
        return quntite;
    }

    public void setQuntite(Float quntite) {
        this.quntite = quntite;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Set<Commande> getCommande() {
        return commande;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCommande(Set<Commande> commande) {
        this.commande = commande;
    }

}
