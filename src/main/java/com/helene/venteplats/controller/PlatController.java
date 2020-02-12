package com.helene.venteplats.controller;

import com.helene.venteplats.model.Plat;
import com.helene.venteplats.service.PlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping(value = "/{id}")
    public void supprimerPlat(@PathVariable int id) {
        platService.supprimerPlat(id);
    }

    @PostMapping
    public void ajouterPlat(@RequestBody Plat plat) {
        platService.insererPlat(plat);
    }

    @PutMapping(value = "/{id}")
    public void modifierPlat (@PathVariable int id, @RequestBody Plat plat) {
        platService.insererPlat(plat);
    }
}
