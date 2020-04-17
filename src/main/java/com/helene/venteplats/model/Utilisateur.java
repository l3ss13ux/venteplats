package com.helene.venteplats.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.helene.venteplats.dto.PlatDTO;
import com.helene.venteplats.dto.UtilisateurDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int idUtilisateur;
    @NotNull
    private String nom;
    @Column(name = "date_anniversaire")
    private LocalDate dateAnniv;

    @OneToMany(mappedBy = "utilisateur", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Plat> plats;

    public Utilisateur() {
    }

    public Utilisateur(int id, String unNom, LocalDate uneDateAnniv) {
        this.idUtilisateur = id;
        this.nom = unNom;
        this.dateAnniv = uneDateAnniv;
    }


    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateAnniv() {
        return dateAnniv;
    }

    public void setDateAnniv(LocalDate dateAnniv) {
        this.dateAnniv = dateAnniv;
    }

    public List<Plat> getPlats() {
        return plats;
    }

    public void setPlats(List<Plat> plats) {
        this.plats = plats;
    }

    public static Utilisateur of(int id, String name, LocalDate aniv) {
        return new Utilisateur(id, name, aniv);
    }

    /*
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Utilisateur) {
            Utilisateur utilisateur = (Utilisateur) obj;

            return utilisateur.getNom().equals(this.nom) && utilisateur.getDateAnniv().equals(this.dateAnniv);
        }
        return false;
    }
    */

}
