package com.helene.venteplats.service;

import com.helene.venteplats.dto.CreationPlatDTO;
import com.helene.venteplats.dto.PlatDTO;
import com.helene.venteplats.model.Plat;
import com.helene.venteplats.model.Utilisateur;
import com.helene.venteplats.repository.PlatRepository;
import com.helene.venteplats.service.critere.CriteresDeRecherche;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;
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
public class PlatServiceTest {
    @InjectMocks
    PlatService platService;

    @Mock
    PlatRepository platRepository;

    // ========== Helper Methods for Test Data Creation ==========

    private Utilisateur createTestUtilisateur(int id, String nom, LocalDate anniv) {
        return new Utilisateur(id, nom, anniv);
    }

    private Utilisateur createDefaultUtilisateur() {
        return createTestUtilisateur(2, "Helene", LocalDate.of(1993, Month.MARCH, 28));
    }

    private Plat createTestPlat(int id, String nom, String type, String description,
                                float prix, LocalDateTime creation, LocalDateTime disponible,
                                Utilisateur utilisateur) {
        return new Plat(id, nom, type, description, prix, creation, disponible, utilisateur);
    }

    private Plat createDefaultPlat(int id, String nom, String type, Utilisateur utilisateur) {
        LocalDateTime creation = LocalDateTime.of(2020, Month.APRIL, 9, 12, 0, 0);
        LocalDateTime disponible = LocalDateTime.of(2020, Month.MAY, 9, 12, 0, 0);
        return createTestPlat(id, nom, type, "fait maison", 3.2F, creation, disponible, utilisateur);
    }

    // ========== Tests ==========

    @Test
    public void testRecupererPlatQuiExiste() {
        Utilisateur utilisateur = createTestUtilisateur(5, "Helene", LocalDate.of(1993, Month.MARCH, 28));
        Plat plat = createDefaultPlat(15, "Gateau au chocolat", "dessert", utilisateur);

        when(platRepository.findById(15)).thenReturn(Optional.of(plat));

        PlatDTO platDTORetourne = platService.recupererPlat(15);

        assertEquals(plat.getIdentifiant(), platDTORetourne.getIdentifiant());
        assertEquals(plat.getNom(), platDTORetourne.getNom());
        assertEquals(plat.getDateDispo(), platDTORetourne.getDisponible());
        assertEquals(plat.getDescription(), platDTORetourne.getDescription());
        assertEquals(plat.getPrix(), platDTORetourne.getPrix());
        assertEquals(plat.getType(), platDTORetourne.getType());
        assertEquals(plat.getUtilisateur().getIdUtilisateur(), platDTORetourne.getIdCreateur());
    }


    @Test
    public void testRecupererPlatQuiNExistePas() {
        when(platRepository.findById(15)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResponseStatusException.class, () -> platService.recupererPlat(15));

        assertEquals("404 NOT_FOUND \"Ce plat n'existe pas en BD\"", exception.getMessage());
    }

    @Test
    public void testRecupererTousLesPlats() {
        Utilisateur utilisateur1 = createTestUtilisateur(1, "Helene", LocalDate.of(1993, Month.MARCH, 28));
        Utilisateur utilisateur2 = createTestUtilisateur(2, "Thomas", LocalDate.of(1991, Month.JANUARY, 22));

        Plat gateauChoco = createDefaultPlat(14, "Gateau chocolat", "dessert", utilisateur1);
        Plat galetteBretonne = createDefaultPlat(15, "galette bretonne", "repas", utilisateur2);

        List<Plat> listePlats = new ArrayList<>();
        listePlats.add(gateauChoco);
        listePlats.add(galetteBretonne);

        when(platRepository.findAll()).thenReturn(listePlats);

        List<PlatDTO> listePlatsDTO = platService.recupererTousLesPlats();

        assertEquals(listePlatsDTO.get(0).getIdentifiant(), listePlats.get(0).getIdentifiant());
        assertEquals(listePlatsDTO.get(0).getNom(), listePlats.get(0).getNom());
        assertEquals(listePlatsDTO.get(0).getType(), listePlats.get(0).getType());
        assertEquals(listePlatsDTO.get(0).getDescription(), listePlats.get(0).getDescription());
        assertEquals(listePlatsDTO.get(0).getDisponible(), listePlats.get(0).getDateDispo());
        assertEquals(listePlatsDTO.get(0).getPrix(), listePlats.get(0).getPrix());
        assertEquals(listePlatsDTO.get(0).getIdCreateur(), listePlats.get(0).getUtilisateur().getIdUtilisateur());

        assertEquals(listePlatsDTO.get(1).getIdentifiant(), listePlats.get(1).getIdentifiant());
        assertEquals(listePlatsDTO.get(1).getNom(), listePlats.get(1).getNom());
        assertEquals(listePlatsDTO.get(1).getType(), listePlats.get(1).getType());
        assertEquals(listePlatsDTO.get(1).getDescription(), listePlats.get(1).getDescription());
        assertEquals(listePlatsDTO.get(1).getDisponible(), listePlats.get(1).getDateDispo());
        assertEquals(listePlatsDTO.get(1).getPrix(), listePlats.get(1).getPrix());
        assertEquals(listePlatsDTO.get(1).getIdCreateur(), listePlats.get(1).getUtilisateur().getIdUtilisateur());
    }

    @Test
    public void testRecupererPlatsUtilisateur() {
        Utilisateur utilisateur = createTestUtilisateur(2, "Thomas", LocalDate.of(1991, Month.JANUARY, 22));

        Plat gateauChoco = createDefaultPlat(14, "Gateau chocolat", "dessert", utilisateur);
        Plat galetteBretonne = createDefaultPlat(15, "galette bretonne", "repas", utilisateur);

        List<Plat> listePlats = new ArrayList<>();
        listePlats.add(gateauChoco);
        listePlats.add(galetteBretonne);

        when(platRepository.getPlatsUnUtilisateur(2)).thenReturn(listePlats);

        List<PlatDTO> listePlatsDTO = platService.recupererPlatsUtilisateur(2);

        assertEquals(gateauChoco.getIdentifiant(), listePlatsDTO.get(0).getIdentifiant());
        assertEquals(gateauChoco.getNom(), listePlatsDTO.get(0).getNom());
        assertEquals(gateauChoco.getDateDispo(), listePlatsDTO.get(0).getDisponible());
        assertEquals(gateauChoco.getDescription(), listePlatsDTO.get(0).getDescription());
        assertEquals(gateauChoco.getPrix(), listePlatsDTO.get(0).getPrix());
        assertEquals(gateauChoco.getType(), listePlatsDTO.get(0).getType());
        assertEquals(gateauChoco.getUtilisateur().getIdUtilisateur(), listePlatsDTO.get(0).getIdCreateur());

        assertEquals(galetteBretonne.getIdentifiant(), listePlatsDTO.get(1).getIdentifiant());
        assertEquals(galetteBretonne.getNom(), listePlatsDTO.get(1).getNom());
        assertEquals(galetteBretonne.getDateDispo(), listePlatsDTO.get(1).getDisponible());
        assertEquals(galetteBretonne.getDescription(), listePlatsDTO.get(1).getDescription());
        assertEquals(galetteBretonne.getPrix(), listePlatsDTO.get(1).getPrix());
        assertEquals(galetteBretonne.getType(), listePlatsDTO.get(1).getType());
        assertEquals(galetteBretonne.getUtilisateur().getIdUtilisateur(), listePlatsDTO.get(1).getIdCreateur());
    }

    @Test
    public void testSupprimerPlat() {
        platService.supprimerPlat(15);

        verify(platRepository, times(1)).deleteById(15);
    }

    @Test
    public void testInsererPlat() {
        LocalDateTime disponible = LocalDateTime.of(2020, Month.APRIL, 25, 12, 00, 00);
        LocalDateTime creation = LocalDateTime.of(2020,Month.APRIL, 19, 20, 00, 00);

        CreationPlatDTO creationPlatDTO = new CreationPlatDTO();
        creationPlatDTO.setNom("gateau au chocolat");
        creationPlatDTO.setType("dessert");
        creationPlatDTO.setPrix(3.2F);
        creationPlatDTO.setDescription("fait maison");
        creationPlatDTO.setDisponible(disponible);

        Utilisateur utilisateur = createDefaultUtilisateur();
        Plat platCree = createTestPlat(15, "gateau au chocolat", "dessert", "fait maison", 3.2F, creation, disponible, utilisateur);

        when(platRepository.save(any(Plat.class))).thenReturn(platCree);

        ArgumentCaptor<Plat> capteurArgumentPlat = ArgumentCaptor.forClass(Plat.class);

        PlatDTO platDTORetourne = platService.insererPlat(creationPlatDTO, 2);

        assertEquals(creationPlatDTO.getNom(), platDTORetourne.getNom());
        assertEquals(creationPlatDTO.getType(), platDTORetourne.getType());
        assertEquals(creationPlatDTO.getDescription(), platDTORetourne.getDescription());
        assertEquals(creationPlatDTO.getPrix(), platDTORetourne.getPrix());
        assertEquals(creationPlatDTO.getDisponible(), platDTORetourne.getDisponible());
        assertEquals(utilisateur.getIdUtilisateur(), platDTORetourne.getIdCreateur());

        verify(platRepository).save(capteurArgumentPlat.capture());

        Plat platSauvegarde = capteurArgumentPlat.getValue();

        assertEquals(creationPlatDTO.getNom(), platSauvegarde.getNom());
        assertEquals(creationPlatDTO.getType(), platSauvegarde.getType());
        assertEquals(creationPlatDTO.getPrix(), platSauvegarde.getPrix());
        assertEquals(creationPlatDTO.getDisponible(), platSauvegarde.getDateDispo());
    }

    @Test
    public void testInsererPlatPrixNull() {
        LocalDateTime disponible = LocalDateTime.of(2020, Month.APRIL, 25, 12, 00, 00);
        CreationPlatDTO creationPlatDTO = new CreationPlatDTO();
        creationPlatDTO.setNom("gateau au chocolat");
        creationPlatDTO.setType("dessert");
        creationPlatDTO.setDescription("fait maison");
        creationPlatDTO.setDisponible(disponible);

        Exception exception = assertThrows(ResponseStatusException.class, () -> platService.insererPlat(creationPlatDTO, 2));

        assertEquals("404 NOT_FOUND \"Le prix ne peut être null\"", exception.getMessage());
    }

    @Test
    public void testModifierPlatQuiExiste() {
        LocalDateTime disponible = LocalDateTime.of(2020, Month.APRIL, 25, 12, 00, 00);
        LocalDateTime creation = LocalDateTime.of(2020, Month.APRIL, 10, 20, 30, 00);

        Utilisateur utilisateur = createDefaultUtilisateur();
        PlatDTO platDTO = new PlatDTO(15, "galette bretonne", "plat", "norvegienne", 11.0F, disponible, 2);
        Plat platBD = createTestPlat(15, "gateau au chocolat", "dessert", "fait maison", 3.2F, creation, disponible, utilisateur);
        Plat platSauvegarde = createTestPlat(15, "galette bretonne", "plat", "norvegienne", 11.0F, creation, disponible, utilisateur);

        when(platRepository.findById(15)).thenReturn(Optional.of(platBD));

        when(platRepository.save(any(Plat.class))).thenReturn(platSauvegarde);

        ArgumentCaptor<Plat> captorPlat = ArgumentCaptor.forClass(Plat.class);

        PlatDTO platDTORetourne = platService.modifierPlat(platDTO, 15);


        assertEquals(platSauvegarde.getIdentifiant(), platDTORetourne.getIdentifiant());
        assertEquals(platSauvegarde.getNom(), platDTORetourne.getNom());
        assertEquals(platSauvegarde.getDescription(), platDTORetourne.getDescription());
        assertEquals(platSauvegarde.getDateDispo(), platDTORetourne.getDisponible());
        assertEquals(platSauvegarde.getPrix(), platDTORetourne.getPrix());
        assertEquals(platSauvegarde.getType(), platDTORetourne.getType());
        assertEquals(platSauvegarde.getUtilisateur().getIdUtilisateur(), platDTORetourne.getIdCreateur());

        verify(platRepository).save(captorPlat.capture());

        verify(platRepository, times(1)).findById(15);

        Plat platCapture = captorPlat.getValue();

        assertEquals(platDTO.getIdentifiant(), platCapture.getIdentifiant());
        assertEquals(platDTO.getNom(), platCapture.getNom());
        assertEquals(platDTO.getType(), platCapture.getType());
        assertEquals(platDTO.getPrix(), platCapture.getPrix());
        assertEquals(platDTO.getDescription(), platCapture.getDescription());
        assertEquals(platDTO.getDisponible(), platCapture.getDateDispo());
        assertEquals(platDTO.getIdCreateur(), platCapture.getUtilisateur().getIdUtilisateur());
    }

    @Test
    public void testModifierPlatQuiNExistePas() {
        LocalDateTime disponible = LocalDateTime.of(2020, Month.APRIL, 25, 12, 00, 00);
        PlatDTO platDTO = new PlatDTO(15, "gateau au chocolat", "dessert", "fait maison", 3.2F, disponible, 2);

        when(platRepository.findById(15)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResponseStatusException.class, () -> platService.modifierPlat(platDTO, 15));

        assertEquals("404 NOT_FOUND \"Ce plat n'existe pas en BD\"", exception.getMessage());
    }

    @Test
    public void testModifierPlatPrixNull() {
        LocalDateTime disponible = LocalDateTime.of(2020, Month.APRIL, 25, 12, 00, 00);
        PlatDTO platDTO = new PlatDTO();
        platDTO.setIdentifiant(15);
        platDTO.setNom("gateau au chocolat");
        platDTO.setType("dessert");
        platDTO.setDescription("fait maison");
        platDTO.setDisponible(disponible);
        platDTO.setIdCreateur(2);

        Exception exception = assertThrows(ResponseStatusException.class, () -> platService.modifierPlat(platDTO, 15));

        assertEquals("404 NOT_FOUND \"Le prix ne peut être null\"", exception.getMessage());

    }

    @Test
    public void testFiltrerPlatsByTypeOnly() {
        Utilisateur utilisateur = createDefaultUtilisateur();

        Plat gateauChoco = createDefaultPlat(1, "Gateau chocolat", "dessert", utilisateur);
        Plat tarte = createDefaultPlat(2, "Tarte aux pommes", "dessert", utilisateur);

        List<Plat> platsFiltered = new ArrayList<>();
        platsFiltered.add(gateauChoco);
        platsFiltered.add(tarte);

        CriteresDeRecherche criteres = new CriteresDeRecherche();
        criteres.setTypeEqual("dessert");

        when(platRepository.findAll(any(Specification.class))).thenReturn(platsFiltered);

        List<PlatDTO> result = platService.filtrerPlats(criteres);

        assertEquals(2, result.size());
        assertEquals("dessert", result.get(0).getType());
        assertEquals("dessert", result.get(1).getType());
        verify(platRepository, times(1)).findAll(any(Specification.class));
    }

    @Test
    public void testFiltrerPlatsByPrixOnly() {
        Utilisateur utilisateur = createDefaultUtilisateur();

        Plat gateauChoco = createDefaultPlat(1, "Gateau chocolat", "dessert", utilisateur);
        Plat tarte = createDefaultPlat(2, "Tarte aux pommes", "dessert", utilisateur);

        List<Plat> platsFiltered = new ArrayList<>();
        platsFiltered.add(gateauChoco);
        platsFiltered.add(tarte);

        CriteresDeRecherche criteres = new CriteresDeRecherche();
        criteres.setPrixInf(10.0F);

        when(platRepository.findAll(any(Specification.class))).thenReturn(platsFiltered);

        List<PlatDTO> result = platService.filtrerPlats(criteres);

        assertEquals(2, result.size());
        assertTrue(result.get(0).getPrix() <= 10.0F);
        assertTrue(result.get(1).getPrix() <= 10.0F);
        verify(platRepository, times(1)).findAll(any(Specification.class));
    }

    @Test
    public void testFiltrerPlatsByTypeAndPrix() {
        Utilisateur utilisateur = createDefaultUtilisateur();

        Plat gateauChoco = createDefaultPlat(1, "Gateau chocolat", "dessert", utilisateur);

        List<Plat> platsFiltered = new ArrayList<>();
        platsFiltered.add(gateauChoco);

        CriteresDeRecherche criteres = new CriteresDeRecherche();
        criteres.setTypeEqual("dessert");
        criteres.setPrixInf(10.0F);

        when(platRepository.findAll(any(Specification.class))).thenReturn(platsFiltered);

        List<PlatDTO> result = platService.filtrerPlats(criteres);

        assertEquals(1, result.size());
        assertEquals("dessert", result.get(0).getType());
        assertTrue(result.get(0).getPrix() <= 10.0F);
        verify(platRepository, times(1)).findAll(any(Specification.class));
    }

    @Test
    public void testFiltrerPlatsWithEmptyCriteria() {
        Utilisateur utilisateur = createDefaultUtilisateur();

        Plat plat1 = createDefaultPlat(1, "Gateau", "dessert", utilisateur);
        Plat plat2 = createDefaultPlat(2, "Pizza", "plat", utilisateur);

        List<Plat> allPlats = new ArrayList<>();
        allPlats.add(plat1);
        allPlats.add(plat2);

        CriteresDeRecherche criteres = new CriteresDeRecherche();

        when(platRepository.findAll(any(Specification.class))).thenReturn(allPlats);

        List<PlatDTO> result = platService.filtrerPlats(criteres);

        assertEquals(2, result.size());
        verify(platRepository, times(1)).findAll(any(Specification.class));
    }

    @Test
    public void testFiltrerPlatsByDate() {
        LocalDateTime creation = LocalDateTime.of(2020, Month.APRIL, 1, 12, 0, 0);
        LocalDateTime disponible1 = LocalDateTime.of(2020, Month.MAY, 1, 12, 0, 0);
        LocalDateTime dateFiltre = LocalDateTime.of(2020, Month.MAY, 10, 12, 0, 0);

        Utilisateur utilisateur = createDefaultUtilisateur();
        Plat platDispo1 = createTestPlat(1, "Gateau chocolat", "dessert", "fait maison", 5.0F, creation, disponible1, utilisateur);

        List<Plat> platsFiltered = new ArrayList<>();
        platsFiltered.add(platDispo1);

        when(platRepository.findAll(any(Specification.class))).thenReturn(platsFiltered);

        List<PlatDTO> result = platService.filtrerPlatsDate(dateFiltre);

        assertEquals(1, result.size());
        assertTrue(result.get(0).getDisponible().isBefore(dateFiltre));
        verify(platRepository, times(1)).findAll(any(Specification.class));
    }
}
