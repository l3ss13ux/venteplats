package com.helene.venteplats.service;

import com.helene.venteplats.model.Plat;
import com.helene.venteplats.repository.PlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlatService {
    @Autowired
    PlatRepository platRepository;

    public Plat recupererPlat(int id){
        Optional<Plat> optionalPlat = platRepository.findById(id);
        if (optionalPlat.isPresent()) {
            return optionalPlat.get();
        }
        return null;
    }

    public List<Plat> recupererTousLesPlats() {
        return platRepository.findAll();
    }

    public List<Plat> recupererPlatsUtilisateur(int idUtilisateur) {
        /*int id;
        List<Plat> platsUtilisateur = new ArrayList<>();
        List<Plat> tousLesPlats = recupererTousLesPlats();
        for (Plat plat : tousLesPlats) {
            id = plat.getIdUtilisateur();
            if (id == idUtilisateur) {
                platsUtilisateur.add(plat);
            }
        }
        return platsUtilisateur;
         */
        return platRepository.getPlatsPourUtilisateur(idUtilisateur);
    }

    public void supprimerPlat(int id) {
        platRepository.deleteById(id);
    }

    public void insererPlat(Plat plat) {
            platRepository.save(plat);
    }
}
