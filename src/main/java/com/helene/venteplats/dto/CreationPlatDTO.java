package com.helene.venteplats.dto;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class CreationPlatDTO {
    @NotEmpty(message = "le nom doit être renseigné")
    @Size(min = 3, max = 27, message = "le nom du plat doit comporter entre 3 et 27 caractères")
    private String nom;
    @NotEmpty(message = "le type doit être renseigné")
    private String type;
    private String description = "";
    @NotNull(message = "le prix doit être renseigné")
    @Digits(integer = 2, fraction = 4)
    private Integer prix;
    @NotNull(message = "la date de disponibilité doit être renseignée")
    @Future
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

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public LocalDateTime getDisponible() {
        return disponible;
    }

    public void setDisponible(LocalDateTime disponible) {
        this.disponible = disponible;
    }
}
