package com.helene.venteplats.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "utilisateur")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Utilisateur implements Serializable {
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

    public Utilisateur(int user_id, String name, LocalDate birthday) {
        this.idUtilisateur = user_id;
        this.nom = name;
        this.dateAnniv = birthday;
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
}
