package com.helene.venteplats.service.critere;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CriteresDeRecherche {
    private String typeEqual;
    private float prixInf;
    private LocalDateTime disponibleAvant;

    public String getTypeEqual() {
        return typeEqual;
    }

    public void setTypeEqual(String typeEqual) {
        this.typeEqual = typeEqual;
    }


    public float getPrixInf() {
        return prixInf;
    }

    public void setPrixInf(float prixInf) {
        this.prixInf = prixInf;
    }

    public LocalDateTime getDisponibleAvant() {
        return disponibleAvant;
    }

    public void setDisponibleAvant(LocalDateTime disponibleAvant) {
        this.disponibleAvant = disponibleAvant;
    }

}
