package com.helene.venteplats.controller;

import com.helene.venteplats.model.Utilisateur;
import com.helene.venteplats.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping (value = "/utilisateurs")
public class UtilisateurController {
    @Autowired
    UtilisateurService utilisateurService;

    @GetMapping(value = "/{id}")
    public Utilisateur voirUtilisateur(@PathVariable int id) {
        return utilisateurService.recupererUtilisateur(id);
    }

    @DeleteMapping(value = "/{id}")
    public void supprimerUtilisateur(@PathVariable int id, @RequestHeader int idCurrentUser, HttpServletResponse response) throws IOException {
        if (id == idCurrentUser) {
            utilisateurService.supprimerUtilisateur(id);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Fonctionnalité interdite pour cet utilisateur");
        }
    }

    @PostMapping
    public void creerUtilisateur(@RequestBody Utilisateur qq1) {
        utilisateurService.enregistrerUtilisateur(qq1);
    }

    @PutMapping(value = "/{id}")
    public void modifierUtilisateur(@PathVariable int id, @RequestBody Utilisateur qq1) {
        utilisateurService.enregistrerUtilisateur(qq1);
    }
}
