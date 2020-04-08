package com.helene.venteplats.service;

import com.helene.venteplats.dto.CreationUtilisateurDTO;
import com.helene.venteplats.dto.UtilisateurDTO;
import com.helene.venteplats.model.Plat;
import com.helene.venteplats.model.Utilisateur;
import com.helene.venteplats.repository.PlatRepository;
import com.helene.venteplats.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UtilisateurService {
    @Autowired
    UtilisateurRepository utilisateurRepository;

    public Optional<Utilisateur> retourneUtilisateur(int id) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(id);

        if (!optionalUtilisateur.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cet utilisateur n'existe pas en BD");
        }
        return optionalUtilisateur;
    }

    public UtilisateurDTO recupererUtilisateur(int id) {
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        return utilisateurDTO.objetToDTO(this.retourneUtilisateur(id).get());
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

    public UtilisateurDTO modifiererUtilisateur(UtilisateurDTO utilisateurDTO, int id) {
        Utilisateur utilisateur = this.retourneUtilisateur(id).get();
        utilisateur.setNom(utilisateurDTO.getNom());
        utilisateur.setDateAnniv(utilisateurDTO.getAnniversaire());
        return UtilisateurDTO.objetToDTO(utilisateurRepository.save(utilisateur));

    }
}
