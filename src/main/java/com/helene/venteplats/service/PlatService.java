package com.helene.venteplats.service;

import com.helene.venteplats.dto.CreationPlatDTO;
import com.helene.venteplats.dto.PlatDTO;
import com.helene.venteplats.service.critere.CriteresDeRecherche;
import com.helene.venteplats.model.Plat;
import com.helene.venteplats.model.Utilisateur;
import com.helene.venteplats.repository.PlatRepository;
import com.helene.venteplats.service.critere.SearchCriteria;
import com.helene.venteplats.service.specifications.PlatSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        return PlatDTO.listeObjetToDTO(platRepository.findAll());
    }

    public List<PlatDTO> recupererPlatsUtilisateur(int idUtilisateur) {
        return PlatDTO.listeObjetToDTO(platRepository.getPlatsUnUtilisateur(idUtilisateur));
    }

    public void supprimerPlat(int id) {
        platRepository.deleteById(id);
    }

    public PlatDTO insererPlat(CreationPlatDTO creationPlatDTO, int id) {
        if (creationPlatDTO.getPrix() == 0.0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le prix ne peut être null");
        }

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

    public PlatDTO modifierPlat(PlatDTO nouveauPlatDTO, int id) {
        if (nouveauPlatDTO.getPrix() == 0.0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le prix ne peut être null");
        }

        PlatDTO ancienPlatDTO = recupererPlat(id);
        ancienPlatDTO.setNom(nouveauPlatDTO.getNom());
        ancienPlatDTO.setType(nouveauPlatDTO.getType());
        ancienPlatDTO.setDescription(nouveauPlatDTO.getDescription());
        ancienPlatDTO.setPrix(nouveauPlatDTO.getPrix());
        ancienPlatDTO.setDisponible(nouveauPlatDTO.getDisponible());
        platRepository.save(Plat.dtoToObjet(ancienPlatDTO));
        return ancienPlatDTO;
    }

    public List<PlatDTO> filtrerPlats(CriteresDeRecherche criteresDeRecherche) {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setKey("type");
        searchCriteria.setOperation("=");
        searchCriteria.setStringValue(criteresDeRecherche.getTypeEqual());
        PlatSpecification platSpecification = new PlatSpecification(searchCriteria);
        return PlatDTO.listeObjetToDTO(platRepository.findAll(Specification.where(platSpecification)));
    }

    public List<PlatDTO> filtrerPlatsPrix(CriteresDeRecherche criteresDeRecherche) {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setKey("prix");
        searchCriteria.setOperation("<=");
        searchCriteria.setPrixValue(criteresDeRecherche.getPrixInf());
        PlatSpecification platSpecification = new PlatSpecification(searchCriteria);
        return PlatDTO.listeObjetToDTO(platRepository.findAll(Specification.where(platSpecification)));
    }

}
