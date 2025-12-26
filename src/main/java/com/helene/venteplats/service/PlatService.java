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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlatService {
    @Autowired
    PlatRepository platRepository;

    public PlatDTO recupererPlat(int id){
        return PlatDTO.objetToDTO(this.retournePlat(id).get());
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

    public PlatDTO modifierPlat(PlatDTO platDTO, int id) {
        if (platDTO.getPrix() == 0.0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le prix ne peut être null");
        }

        Plat plat = this.retournePlat(id).get();
        plat.setNom(platDTO.getNom());
        plat.setType(platDTO.getType());
        plat.setPrix(platDTO.getPrix());
        plat.setDescription(platDTO.getDescription());
        plat.setDateDispo(platDTO.getDisponible());
        return PlatDTO.objetToDTO(platRepository.save(plat));
    }

    public List<PlatDTO> filtrerPlats(CriteresDeRecherche critere) {
        SearchCriteria searchCriteria1 = new SearchCriteria();
        SearchCriteria searchCriteria2 = new SearchCriteria();
        //SearchCriteria searchCriteria3 = new SearchCriteria();
        if (critere.getTypeEqual() != null ) {
            searchCriteria1.setKey("type");
            searchCriteria1.setOperation("=");
            searchCriteria1.setStringValue(critere.getTypeEqual());
        }
        if (critere.getPrixInf() != 0.0) {
            searchCriteria2.setKey("prix");
            searchCriteria2.setOperation("<=");
            searchCriteria2.setPrixValue(critere.getPrixInf());
        }
        /*
        if (critere.getDisponibleAvant() != null) {
            searchCriteria3.setKey("dateDispo");
            searchCriteria3.setOperation("<");
            searchCriteria3.setDateValue(critere.getDisponibleAvant());
        }
*/

        PlatSpecification platSpecification1 = new PlatSpecification(searchCriteria1);

        PlatSpecification platSpecification2 = new PlatSpecification(searchCriteria2);

        //PlatSpecification platSpecification3 = new PlatSpecification(searchCriteria3);

        return PlatDTO.listeObjetToDTO(platRepository.findAll(Specification.where(platSpecification1)
                .and(platSpecification2)));

        /*
        return PlatDTO.listeObjetToDTO(platRepository.findAll(Specification.where(platSpecification1)
                .and(platSpecification2)
                .and(platSpecification3)));

         */
    }
    
    public List<PlatDTO> filtrerPlatsDate(LocalDateTime dateTime) {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setKey("dateDispo");
        searchCriteria.setOperation("<");
        searchCriteria.setDateValue(dateTime);
        PlatSpecification platSpecification = new PlatSpecification(searchCriteria);
        return PlatDTO.listeObjetToDTO(platRepository.findAll(Specification.where(platSpecification)));
    }

    private Optional<Plat> retournePlat(int id) {
        Optional<Plat> optionalPlat = platRepository.findById(id);
        if (!optionalPlat.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ce plat n'existe pas en BD");
        }
        return optionalPlat;
    }

}
