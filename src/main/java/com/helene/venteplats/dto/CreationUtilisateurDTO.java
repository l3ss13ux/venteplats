package com.helene.venteplats.dto;

import com.helene.venteplats.model.Plat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreationUtilisateurDTO {
    private String nom;
    private LocalDate anniv;

    public CreationUtilisateurDTO() {}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getAnniv() {
        return anniv;
    }

    public void setAnniv(LocalDate anniv) {
        this.anniv = anniv;
    }

}
