package com.helene.venteplats.service;

import com.helene.venteplats.dto.PlatDTO;
import com.helene.venteplats.model.Plat;
import com.helene.venteplats.model.Utilisateur;
import com.helene.venteplats.repository.PlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlatService {
    @Autowired
    PlatRepository platRepository;

    public PlatDTO recupererPlat(int id){
        PlatDTO platDTO = new PlatDTO();
        Optional<Plat> optionalPlat = platRepository.findById(id);
        if (optionalPlat.isPresent()) {
            return platDTO.objetToDTO(optionalPlat.get());
        }
        return null;
    }

    public List<PlatDTO> recupererTousLesPlats() {
        return PlatDTO.listeObjetToTDO(platRepository.findAll());
    }

    public List<PlatDTO> recupererPlatsUtilisateur(int idUtilisateur) {
        return PlatDTO.listeObjetToTDO(platRepository.getPlatsUnUtilisateur(idUtilisateur));
    }

    public void supprimerPlat(int id) {
        platRepository.deleteById(id);
    }

    public void insererPlat(PlatDTO platDTO) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdUtilisateur(platDTO.getIdCreateur());
        Plat plat = new Plat();
        plat.setIdentifiant(platDTO.getIdentifiant());
        plat.setNom(platDTO.getNom());
        plat.setPrix(platDTO.getPrix());
        plat.setDescription(platDTO.getDescription());
        plat.setDateDispo(platDTO.getDisponible());
        plat.setType(platDTO.getType());
        plat.setUtilisateur(utilisateur);
        platRepository.save(plat);
    }
}
