package com.helene.venteplats.controller;

import com.helene.venteplats.dto.CreationUtilisateurDTO;
import com.helene.venteplats.dto.UtilisateurDTO;
import com.helene.venteplats.model.Utilisateur;
import com.helene.venteplats.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
    public UtilisateurDTO creerUtilisateur(@RequestBody @Valid CreationUtilisateurDTO creationUtilisateurDTO) {
        return utilisateurService.enregistrerUtilisateur(creationUtilisateurDTO);
    }

    @PutMapping(value = "/{id}")
    public UtilisateurDTO modifierUtilisateur(@PathVariable int id, @RequestBody @Valid UtilisateurDTO qq1,
                                    @RequestHeader int idCurrentUser, HttpServletResponse reponse) throws IOException {
        if (id == idCurrentUser) {
            return utilisateurService.modifiererUtilisateur(qq1, id);
        } else {
            reponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Cet utilisateur n'a pas le droit de réaliser cette action");
            return null;
        }
    }
}
