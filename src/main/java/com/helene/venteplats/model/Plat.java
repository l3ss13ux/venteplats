package com.helene.venteplats.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "plat")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Plat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int identifiant;
    private String nom;
    private String type;
    private String description;
    private float prix;
    @CreationTimestamp
    @Column(name = "date_creation")
    private LocalDateTime dateCreation;
    @Column(name = "date_dispo")
    private LocalDateTime dateDispo;

    public Plat(int id, String name, String kind, String desc, float price, LocalDateTime createdDate, LocalDateTime availableDate) {
        this.identifiant = id;
        this.nom = name;
        this.type = kind;
        this.description = desc;
        this.prix = price;
        this.dateCreation = createdDate;
        this.dateDispo = availableDate;
    }

    public Plat() {

    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateDispo() {
        return dateDispo;
    }

    public void setDateDispo(LocalDateTime dateDispo) {
        this.dateDispo = dateDispo;
    }
}
