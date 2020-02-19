package com.helene.venteplats.service;

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

    public void enregistrerUtilisateur(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdUtilisateur(utilisateurDTO.getIdentifiant());
        utilisateur.setNom(utilisateurDTO.getNom());
        utilisateur.setDateAnniv(utilisateurDTO.getAnniversaire());
        utilisateur.setPlats(Plat.listeDtoToObjet(utilisateurDTO.getPlats()));
        utilisateurRepository.save(utilisateur);
    }
}
