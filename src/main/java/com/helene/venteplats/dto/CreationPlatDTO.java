package com.helene.venteplats.dto;

import java.time.LocalDateTime;

public class CreationPlatDTO {
    private String nom, type;
    private String description = "";
    private int prix;
    private LocalDateTime disponible;

    public CreationPlatDTO() {}

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

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public LocalDateTime getDisponible() {
        return disponible;
    }

    public void setDisponible(LocalDateTime disponible) {
        this.disponible = disponible;
    }
}
