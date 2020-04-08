package com.helene.venteplats.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.helene.venteplats.dto.PlatDTO;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plat")
public class Plat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int identifiant;
    @NotNull
    private String nom;
    @NotNull
    private String type;
    private String description;
    @NotNull
    private float prix;
    @CreationTimestamp
    @Column(name = "date_creation")
    private LocalDateTime dateCreation;
    @Column(name = "date_dispo")
    @NotNull
    private LocalDateTime dateDispo;

    @NotNull
    @ManyToOne
    private Utilisateur utilisateur;

    public Plat(int id, String name, String kind, String desc, float price, LocalDateTime createdDate,
                LocalDateTime availableDate, Utilisateur utilisateur) {
        this.identifiant = id;
        this.nom = name;
        this.type = kind;
        this.description = desc;
        this.prix = price;
        this.dateCreation = createdDate;
        this.dateDispo = availableDate;
        this.utilisateur = utilisateur;
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

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

}
