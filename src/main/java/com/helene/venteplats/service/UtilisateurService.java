package com.helene.venteplats.service;

import com.helene.venteplats.dto.CreationUtilisateurDTO;
import com.helene.venteplats.dto.UtilisateurDTO;
import com.helene.venteplats.model.Plat;
import com.helene.venteplats.model.Utilisateur;
import com.helene.venteplats.repository.PlatRepository;
import com.helene.venteplats.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService {
    @Autowired
    UtilisateurRepository utilisateurRepository;

    public UtilisateurDTO recupererUtilisateur(int id) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(id);

        if (optionalUtilisateur.isPresent()) {
            UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
            return utilisateurDTO.objetToDTO(optionalUtilisateur.get());

        }
        return null;
    }

    public void supprimerUtilisateur(int id) {
        utilisateurRepository.deleteById(id);
    }

    public UtilisateurDTO enregistrerUtilisateur(CreationUtilisateurDTO creationUtilisateurDTO) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(creationUtilisateurDTO.getNom());
        utilisateur.setDateAnniv(creationUtilisateurDTO.getAnniv());
        return UtilisateurDTO.objetToDTO(utilisateurRepository.save(utilisateur));
    }

    public UtilisateurDTO modifiererUtilisateur(UtilisateurDTO nouvelUtilisateurDTO, int id) {
        UtilisateurDTO ancienUtilisateurDTO = recupererUtilisateur(id);
        ancienUtilisateurDTO.setNom(nouvelUtilisateurDTO.getNom());
        ancienUtilisateurDTO.setAnniversaire(nouvelUtilisateurDTO.getAnniversaire());
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdUtilisateur(ancienUtilisateurDTO.getIdentifiant());
        utilisateur.setNom(ancienUtilisateurDTO.getNom());
        utilisateur.setDateAnniv(ancienUtilisateurDTO.getAnniversaire());
        utilisateur.setPlats(Plat.listeDtoToObjet(ancienUtilisateurDTO.getPlats()));
        utilisateurRepository.save(utilisateur);
        return ancienUtilisateurDTO;

    }
}
