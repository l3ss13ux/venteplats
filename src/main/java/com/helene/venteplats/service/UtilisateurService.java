package com.helene.venteplats.service;

import com.helene.venteplats.model.Utilisateur;
import com.helene.venteplats.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {
    @Autowired
    UtilisateurRepository utilisateurRepository;

    public Utilisateur recupererUtilisateur(int id) {
        return utilisateurRepository.getOne(id);
    }

    public void supprimerUtilisateur(int id) {
        utilisateurRepository.deleteById(id);
    }

    public void enregistrerUtilisateur(Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
    }
}
