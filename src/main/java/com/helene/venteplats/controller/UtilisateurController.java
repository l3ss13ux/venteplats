package com.helene.venteplats.controller;

import com.helene.venteplats.model.Utilisateur;
import com.helene.venteplats.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping (value = "/utilisateur")
public class UtilisateurController {
    @Autowired
    UtilisateurService utilisateurService;

    @GetMapping(value = "/{id}")
    public Utilisateur voirUtilisateur(@PathVariable int id) {
        return utilisateurService.recupererUtilisateur(id);
    }

    @GetMapping
    public List<Utilisateur> voirTousLesUtilisateurs() {
        return utilisateurService.recupererTousLesUtilisateurs();
    }
}
