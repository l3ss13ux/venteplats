package com.helene.venteplats.service;

import com.helene.venteplats.dto.CreationUtilisateurDTO;
import com.helene.venteplats.dto.PlatDTO;
import com.helene.venteplats.dto.UtilisateurDTO;
import com.helene.venteplats.model.Plat;
import com.helene.venteplats.model.Utilisateur;
import com.helene.venteplats.repository.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UtilisateurServiceTest {
    @InjectMocks
    UtilisateurService utilisateurService;

    @Mock
    UtilisateurRepository utilisateurRepository;



    @Test
    public void testRecupererUtilisateurQuiExiste() {
        LocalDate dateAnniv = LocalDate.of(1993, Month.MARCH, 28);
        LocalDateTime disponible = LocalDateTime.of(2020,Month.APRIL, 14, 18,00,00);
        LocalDateTime creation = LocalDateTime.of(2020,Month.MARCH, 1, 13,45,00);

        Utilisateur utilisateur = new Utilisateur(5, "Helene", dateAnniv);

        Plat plat = new Plat(12, "gateau au citron", "dessert", "fait maison", 5.2F, creation, disponible, utilisateur);
        List<Plat> listePlats = new ArrayList<Plat>();
        listePlats.add(plat);

        utilisateur.setPlats(listePlats);

        when(utilisateurRepository.findById(5)).thenReturn(Optional.of(utilisateur));

        UtilisateurDTO utilisateurDTORetourne = utilisateurService.recupererUtilisateur(5);

        assertEquals(utilisateur.getIdUtilisateur(), utilisateurDTORetourne.getIdentifiant());
        assertEquals(utilisateur.getNom(), utilisateurDTORetourne.getNom());
        assertEquals(utilisateur.getDateAnniv(), utilisateurDTORetourne.getAnniversaire());
        assertEquals(utilisateur.getPlats().size(), utilisateurDTORetourne.getPlats().size());
    }




    @Test
    public void testRecupererUtilisateurQuiNExistePas() {
        when(utilisateurRepository.findById(5)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResponseStatusException.class, () -> utilisateurService.recupererUtilisateur(5));

        assertEquals("404 NOT_FOUND \"Cet utilisateur n'existe pas en BD\"", exception.getMessage());
    }


    @Test
    public void testSupprimerUtilisateur() {
        utilisateurService.supprimerUtilisateur(5);

        verify(utilisateurRepository, times(1)).deleteById(5);
    }


    @Test
    public void testEnregistrerUtilisateur() {
        LocalDate anniv = LocalDate.of(1993, Month.MARCH, 28);
        CreationUtilisateurDTO creationUtilisateurDTO = new CreationUtilisateurDTO("Helene", anniv);

        Utilisateur utilisateur = new Utilisateur(5, "Helene", anniv);

        when(utilisateurRepository.save(any(Utilisateur.class))).thenReturn(utilisateur);

        ArgumentCaptor<Utilisateur> userArgumentCaptor = ArgumentCaptor.forClass(Utilisateur.class);

        UtilisateurDTO utilisateurDTORetourne = utilisateurService.enregistrerUtilisateur(creationUtilisateurDTO);

        assertEquals(utilisateur.getIdUtilisateur(), utilisateurDTORetourne.getIdentifiant());
        assertEquals(creationUtilisateurDTO.getNom(), utilisateurDTORetourne.getNom());
        assertEquals(creationUtilisateurDTO.getAnniv(), utilisateurDTORetourne.getAnniversaire());
        assertEquals(0, utilisateurDTORetourne.getPlats().size());

        //capture() permet de récupérer l'argument de la méthode save()
        //c'est forcément dans une vérification
        //peut ensuite être utilisé pour une assertion
        verify(utilisateurRepository).save(userArgumentCaptor.capture());

        Utilisateur savedUser = userArgumentCaptor.getValue();

        assertEquals(creationUtilisateurDTO.getNom(), savedUser.getNom());
        assertEquals(creationUtilisateurDTO.getAnniv(),savedUser.getDateAnniv());
    }


    @Test
    public void testModifierUtilisateurSansPLats() {
        LocalDate anniv = LocalDate.of(1993, Month.MARCH, 28);

        UtilisateurDTO utilisateurDTO = new UtilisateurDTO(5, "Helene", anniv, new ArrayList<PlatDTO>());

        Utilisateur utilisateurDB = new Utilisateur(5, "Thomas", anniv);
        Utilisateur utilisateurSaved = new Utilisateur(5, "Helene", anniv);

        when(utilisateurRepository.findById(5)).thenReturn(Optional.of(utilisateurDB));

        when(utilisateurRepository.save(any(Utilisateur.class))).thenReturn(utilisateurSaved);

        ArgumentCaptor<Utilisateur> argumentCaptor = ArgumentCaptor.forClass(Utilisateur.class);

        UtilisateurDTO utilisateurDTORetourne = utilisateurService.modifiererUtilisateur(utilisateurDTO, 5);

        verify(utilisateurRepository).save(argumentCaptor.capture());

        Utilisateur utilisateurCapture = argumentCaptor.getValue();

        assertEquals(utilisateurDTO.getNom(), utilisateurCapture.getNom());
        assertEquals(utilisateurDTO.getAnniversaire(), utilisateurCapture.getDateAnniv());

        assertEquals(utilisateurSaved.getIdUtilisateur(), utilisateurDTORetourne.getIdentifiant());
        assertEquals(utilisateurSaved.getNom(), utilisateurDTORetourne.getNom());
        assertEquals(utilisateurSaved.getDateAnniv(), utilisateurDTORetourne.getAnniversaire());
        assertEquals(0, utilisateurDTORetourne.getPlats().size());
    }


    @Test
    public void testModifierUtilisateurAvecPLats() {
        LocalDate anniv = LocalDate.of(1993, Month.MARCH, 28);
        LocalDateTime creation = LocalDateTime.of(2020, Month.APRIL, 17, 12, 00, 00);
        LocalDateTime disponible = LocalDateTime.of(2020, Month.APRIL, 18, 12, 00, 00);
        Utilisateur utilisateurDB = new Utilisateur(5, "Thomas", anniv);
        Plat plat = new Plat(17, "Gateau au chocolat", "dessert", "fait maison", 3.2F, creation, disponible, utilisateurDB);
        List<Plat> listePlats = new ArrayList<Plat>();
        listePlats.add(plat);
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setIdentifiant(5);
        utilisateurDTO.setNom("Helene");
        utilisateurDTO.setAnniversaire(anniv);


        utilisateurDB.setPlats(listePlats);
        Utilisateur utilisateurSaved = new Utilisateur(5, "Helene", anniv);
        utilisateurSaved.setPlats(listePlats);

        when(utilisateurRepository.findById(5)).thenReturn(Optional.of(utilisateurDB));

        when(utilisateurRepository.save(any(Utilisateur.class))).thenReturn(utilisateurSaved);

        ArgumentCaptor<Utilisateur> argumentCaptor = ArgumentCaptor.forClass(Utilisateur.class);

        UtilisateurDTO utilisateurDTORetourne = utilisateurService.modifiererUtilisateur(utilisateurDTO, 5);

        verify(utilisateurRepository).save(argumentCaptor.capture());

        Utilisateur utilisateurCapture = argumentCaptor.getValue();

        assertEquals(utilisateurDTO.getNom(), utilisateurCapture.getNom());
        assertEquals(utilisateurDTO.getAnniversaire(), utilisateurCapture.getDateAnniv());

        assertEquals(utilisateurSaved.getIdUtilisateur(), utilisateurDTORetourne.getIdentifiant());
        assertEquals(utilisateurSaved.getNom(), utilisateurDTORetourne.getNom());
        assertEquals(utilisateurSaved.getDateAnniv(), utilisateurDTORetourne.getAnniversaire());
        assertEquals(1, utilisateurDTORetourne.getPlats().size());
    }



    @Test
    public void testModifierUtilisateurQuiNExistePas() {
        LocalDate anniv = LocalDate.of(1993, Month.MARCH, 28);

        UtilisateurDTO utilisateurDTO = new UtilisateurDTO(5, "Helene", anniv, new ArrayList<PlatDTO>());

        when(utilisateurRepository.findById(5)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResponseStatusException.class, () -> utilisateurService.modifiererUtilisateur(utilisateurDTO, 5));

        assertEquals("404 NOT_FOUND \"Cet utilisateur n'existe pas en BD\"", exception.getMessage());
    }
}
