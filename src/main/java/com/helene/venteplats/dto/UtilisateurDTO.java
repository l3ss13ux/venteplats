package com.helene.venteplats.dto;

import com.helene.venteplats.model.Utilisateur;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public class UtilisateurDTO {
    @NotNull(message = "L'identifiant ne peut être null")
    private int identifiant;
    @NotEmpty
    @Size(min = 3, max = 27, message = "entre 3 et 27 caractères")
    private String nom;
    @Past
    private LocalDate anniversaire;
    private List<PlatDTO> plats;

    public UtilisateurDTO() {}

    public UtilisateurDTO(int id, String name, LocalDate birthday, List<PlatDTO> liste) {
        this.identifiant = id;
        this.nom = name;
        this.anniversaire = birthday;
        this.plats = liste;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public List<PlatDTO> getPlats() {
        return plats;
    }

    public void setPlats(List<PlatDTO> plats) {
        this.plats = plats;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getAnniversaire() {
        return anniversaire;
    }

    public void setAnniversaire(LocalDate anniversaire) {
        this.anniversaire = anniversaire;
    }

    public static UtilisateurDTO objetToDTO(Utilisateur utilisateur) {
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setIdentifiant(utilisateur.getIdUtilisateur());
        utilisateurDTO.setNom(utilisateur.getNom());
        utilisateurDTO.setAnniversaire(utilisateur.getDateAnniv());
        utilisateurDTO.setPlats(PlatDTO.listeObjetToDTO(utilisateur.getPlats()));
        return utilisateurDTO;
    }

}
