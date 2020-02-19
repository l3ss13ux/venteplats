package com.helene.venteplats.controller;

import com.helene.venteplats.dto.PlatDTO;
import com.helene.venteplats.dto.UtilisateurDTO;
import com.helene.venteplats.service.PlatService;
import com.helene.venteplats.service.UtilisateurService;
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
    @Autowired
    UtilisateurService utilisateurService;

    @GetMapping(value = "/{id}")
    public PlatDTO voirPlat(@PathVariable int id) {
        return platService.recupererPlat(id);
    }

    @GetMapping
    public List<PlatDTO> listerPlats() {
        return platService.recupererTousLesPlats();
    }

    @GetMapping (value = "/utilisateur/{id}")
    public List<PlatDTO> listePlatsUtilisateur(@PathVariable int id,
                                            @RequestHeader int idCurrentUser, HttpServletResponse reponse) throws IOException {
        if(id == idCurrentUser) {
            return platService.recupererPlatsUtilisateur(id);
        } else {
            reponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Cet utilisateur n'a pas le droit de réaliser cette action");
            return null;
        }
    }

    @DeleteMapping(value = "/{id}")
    public void supprimerPlat(@PathVariable int id, @RequestHeader int idCurrentUser, HttpServletResponse reponse) throws IOException {
        int idUtilisateur = platService.recupererPlat(id).getIdCreateur();
        if (idUtilisateur == idCurrentUser) {
            platService.supprimerPlat(id);
        } else {
            reponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Cet utilisateur n'a pas le droit de réaliser cette action");
        }
    }

    @PostMapping
    public void ajouterPlat(@RequestBody PlatDTO platDTO)
            throws IOException {
        platService.insererPlat(platDTO);
    }

    @PutMapping(value = "/{id}")
    public void modifierPlat (@PathVariable int id, @RequestBody PlatDTO platDTO,
                              @RequestHeader int idCurrentUser, HttpServletResponse reponse) throws IOException {
        if (platDTO.getIdCreateur() == idCurrentUser) {
            platService.insererPlat(platDTO);
        } else {
            reponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Cet utilisateur n'a pas le droit de réaliser cette action");
        }
    }
}
