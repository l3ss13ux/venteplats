package com.helene.venteplats.controller;

import com.helene.venteplats.dto.CreationPlatDTO;
import com.helene.venteplats.dto.PlatDTO;
import com.helene.venteplats.service.critere.CriteresDeRecherche;
import com.helene.venteplats.service.PlatService;
import com.helene.venteplats.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
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

    @GetMapping(value = "/filtre/type")
    public List<PlatDTO> listerPlats(CriteresDeRecherche criteresDeRecherche) {
        return platService.filtrerPlats(criteresDeRecherche);
    }

    @GetMapping(value = "/filtre/prix")
    public List<PlatDTO> listerPlatsBis(CriteresDeRecherche criteresDeRecherche) {
        return platService.filtrerPlatsPrix(criteresDeRecherche);
    }

    @GetMapping(value = "/filtre/date")
    public List<PlatDTO> listerPlatsTer(@RequestParam("disponibleAvant")
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                        LocalDateTime dateTime) {
        return platService.filtrerPlatsDate(dateTime);
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
    public PlatDTO ajouterPlat(@RequestBody @Valid CreationPlatDTO creationPlatDTO, @RequestHeader int idCurrentUser)
            throws IOException {
        return platService.insererPlat(creationPlatDTO, idCurrentUser);
    }


    @PutMapping(value = "/{id}")
    public PlatDTO modifierPlat (@PathVariable int id, @RequestBody @Valid PlatDTO platDTO,
                              @RequestHeader int idCurrentUser, HttpServletResponse reponse) throws IOException {
        int idCreateurPlat = platService.recupererPlat(id).getIdCreateur();
        if (idCreateurPlat == idCurrentUser) {
            return platService.modifierPlat(platDTO, id);
        } else {
            reponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Cet utilisateur n'a pas le droit de réaliser cette action");
            return null;
        }
    }

}
