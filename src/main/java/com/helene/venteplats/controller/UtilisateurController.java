package com.helene.venteplats.controller;

import com.helene.venteplats.dto.UtilisateurDTO;
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
    public UtilisateurDTO voirUtilisateur(@PathVariable int id) {
        return utilisateurService.recupererUtilisateur(id);
    }

    @DeleteMapping(value = "/{id}")
    public void supprimerUtilisateur(@PathVariable int id, @RequestHeader int idCurrentUser, HttpServletResponse response)
            throws IOException {
        if (id == idCurrentUser) {
            utilisateurService.supprimerUtilisateur(id);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Cet utilisateur n'a pas le droit de réaliser cette action");
        }
    }

    @PostMapping
    public void creerUtilisateur(@RequestBody UtilisateurDTO qq1) {
        utilisateurService.enregistrerUtilisateur(qq1);
    }

    @PutMapping(value = "/{id}")
    public void modifierUtilisateur(@PathVariable int id, @RequestBody UtilisateurDTO qq1,
                                    @RequestHeader int idCurrentUser, HttpServletResponse reponse) throws IOException {
        if (id == idCurrentUser) {
            utilisateurService.enregistrerUtilisateur(qq1);
        } else {
            reponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Cet utilisateur n'a pas le droit de réaliser cette action");
        }
    }
}
