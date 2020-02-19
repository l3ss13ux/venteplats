package com.helene.venteplats.dto;

import com.helene.venteplats.model.Plat;

import java.util.List;

public class UtilisateurDTO {
    private int identifiant;
    private List<Plat> plats;

    public UtilisateurDTO() {}

    public UtilisateurDTO(int id, List<Plat> liste) {
        this.identifiant = id;
        this.plats = liste;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public List<Plat> getPlats() {
        return plats;
    }

    public void setPlats(List<Plat> plats) {
        this.plats = plats;
    }
}
