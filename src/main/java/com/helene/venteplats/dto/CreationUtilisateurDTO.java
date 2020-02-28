package com.helene.venteplats.dto;

import com.helene.venteplats.model.Plat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreationUtilisateurDTO {
    @NotEmpty
    @Size(min = 3, max = 27, message = "entre 3 et 27 caractères")
    private String nom;
    @Past
    private LocalDate anniv;

    public CreationUtilisateurDTO() {}

    public CreationUtilisateurDTO(String name, LocalDate birthday) {
        this.nom = name;
        this.anniv = birthday;
    }

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
