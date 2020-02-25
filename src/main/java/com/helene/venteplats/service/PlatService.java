package com.helene.venteplats.service;

import com.helene.venteplats.dto.CreationPlatDTO;
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

    public PlatDTO insererPlat(CreationPlatDTO creationPlatDTO, int id) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdUtilisateur(id);
        Plat plat = new Plat();
        plat.setNom(creationPlatDTO.getNom());
        plat.setType(creationPlatDTO.getType());
        plat.setDescription(creationPlatDTO.getDescription());
        plat.setPrix(creationPlatDTO.getPrix());
        plat.setDateDispo(creationPlatDTO.getDisponible());
        plat.setUtilisateur(utilisateur);
        return PlatDTO.objetToDTO(platRepository.save(plat));
    }

    public PlatDTO modifierPlat(PlatDTO platDTO) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdUtilisateur(platDTO.getIdCreateur());
        Plat plat = new Plat();
        plat.setIdentifiant(platDTO.getIdentifiant());
        plat.setNom(platDTO.getNom());
        plat.setType(platDTO.getType());
        plat.setDescription(platDTO.getDescription());
        plat.setPrix(platDTO.getPrix());
        plat.setDateDispo(platDTO.getDisponible());
        plat.setUtilisateur(utilisateur);
        plat.setDateCreation(platDTO.getCreation());
        return PlatDTO.objetToDTO(platRepository.save(plat));
    }
}
