package com.helene.venteplats.dto;

import java.time.LocalDateTime;

public class PlatDTO {
    private int identifiant;
    private String nom;
    private String type;
    private String description;
    private float prix;
    private LocalDateTime disponible;
    private UtilisateurDTO utilisateur;

    public PlatDTO(){}

    public PlatDTO(int id, String name, String kind, String desc, float price, LocalDateTime available, UtilisateurDTO user) {
        this.identifiant = id;
        this.nom = name;
        this.type = kind;
        this.description = desc;
        this.prix = price;
        this.disponible = available;
        this.utilisateur = user;
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

    public LocalDateTime getDisponible() {
        return disponible;
    }

    public void setDisponible(LocalDateTime disponible) {
        this.disponible = disponible;
    }

    public UtilisateurDTO getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UtilisateurDTO utilisateur) {
        this.utilisateur = utilisateur;
    }
}
