package com.helene.venteplats.controller;

import com.helene.venteplats.model.Plat;
import com.helene.venteplats.service.PlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "plats")
public class PlatController {
    @Autowired
    PlatService platService;

    @GetMapping(value = "/{id}")
    public Plat voirPlat(@PathVariable int id) {
        return platService.recupererPlat(id);
    }

    @GetMapping
    public List<Plat> listerPlats() {
        return platService.recupererTousLesPlats();
    }

    @GetMapping (value = "/listePlats")
    public List<Plat> listePlatsUtilisateur(@RequestHeader int idCurrentUser) {
        return platService.recupererPlatsUtilisateur(idCurrentUser);
    }

    @DeleteMapping(value = "/{id}")
    public void supprimerPlat(@PathVariable int id, @RequestHeader int idCurrentUser, HttpServletResponse reponse) throws IOException {
        int utilisateur = platService.recupererPlat(id).getIdUtilisateur();
        if (utilisateur == idCurrentUser) {
            platService.supprimerPlat(id);
        } else {
            reponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Cet utilisateur n'a pas le droit de réaliser cette action");
        }
    }

    @PostMapping
    public void ajouterPlat(@RequestBody Plat plat) {
        platService.insererPlat(plat);
    }

    @PutMapping(value = "/{id}")
    public void modifierPlat (@PathVariable int id, @RequestBody Plat plat,
                              @RequestHeader int idCurrentUser, HttpServletResponse reponse) throws IOException {
        int utilisateur = platService.recupererPlat(id).getIdUtilisateur();
        if (utilisateur == idCurrentUser) {
            platService.insererPlat(plat);
        } else {
            reponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Cet utilisateur n'a pas le droit de réaliser cette action");
        }
    }
}
