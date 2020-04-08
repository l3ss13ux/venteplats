package com.helene.venteplats.dto;

import com.helene.venteplats.model.Plat;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PlatDTO {
    @NotNull(message = "l'identifiant ne peut être null")
    private int identifiant;
    @NotEmpty(message = "le nom ne peut être null")
    @Size(min = 3, max = 27, message = "le nom du plat doit comporter entre 3 et 27 caractères")
    private String nom;
    @NotEmpty(message = "le type ne peut être null")
    private String type;
    private String description;
    @NotNull(message = "le prix ne peut être null")
    private float prix;
    @NotNull(message = "la date de disponibilité ne peut être nulle")
    @Future
    private LocalDateTime disponible;
    @NotNull(message = "l'identifiant du créateur ne peut être null")
    private int idCreateur;

    public PlatDTO(){}

    public PlatDTO(int id, String name, String kind, String desc, float price, LocalDateTime available,
                   int user) {
        this.identifiant = id;
        this.nom = name;
        this.type = kind;
        this.description = desc;
        this.prix = price;
        this.disponible = available;
        this.idCreateur = user;
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

    public int getIdCreateur() {
        return idCreateur;
    }

    public void setIdCreateur(int idCreateur) {
        this.idCreateur = idCreateur;
    }

    public static PlatDTO objetToDTO(Plat plat) {
        PlatDTO platDTO = new PlatDTO();
        platDTO.setIdentifiant(plat.getIdentifiant());
        platDTO.setNom(plat.getNom());
        platDTO.setDisponible(plat.getDateDispo());
        platDTO.setDescription(plat.getDescription());
        platDTO.setPrix(plat.getPrix());
        platDTO.setType(plat.getType());
        platDTO.setIdCreateur(plat.getUtilisateur().getIdUtilisateur());
        return platDTO;
    }

    public static List<PlatDTO> listeObjetToDTO(List<Plat> plats) {
        List<PlatDTO> platsDto = new ArrayList<PlatDTO>();
        if (!(plats == null)) {
            for (Plat plat : plats) {
                platsDto.add(objetToDTO(plat));
            }
            return platsDto;
        } else {
            return platsDto;
        }
    }
}
