package com.helene.venteplats.service;

import com.helene.venteplats.dto.PlatDTO;
import com.helene.venteplats.model.Plat;
import com.helene.venteplats.model.Utilisateur;
import com.helene.venteplats.repository.PlatRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PlatServiceTest {
    @InjectMocks
    PlatService platService;

    @Mock
    PlatRepository platRepository;

    @Test
    public void testRecupererPlatQuiExiste() throws ResponseStatusException {
        LocalDateTime creation = LocalDateTime.of(2020, Month.APRIL, 9, 12, 00, 00);
        LocalDateTime disponible = LocalDateTime.of(2020, Month.MAY, 9, 12, 00,00);
        LocalDate anniv = LocalDate.of(1993, Month.MARCH, 28);
        Utilisateur utilisateur = new Utilisateur(5, "Helene", anniv);
        Plat plat = new Plat(15, "Gateau au chocolat", "dessert", "fait maison", 3.2F, creation, disponible, utilisateur);

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
    public void testRecupererPlatQuiNExistePas() throws ResponseStatusException {
        when(platRepository.findById(15)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResponseStatusException.class, () -> platService.recupererPlat(15));

        assertEquals("404 NOT_FOUND \"Ce plat n'existe pas en BD\"", exception.getMessage());
    }

    @Test
    public void testRecupererTousLesPlats() {
        LocalDateTime creationGateau = LocalDateTime.of(2020, Month.APRIL, 01, 18, 00, 00);
        LocalDateTime creationGalette = LocalDateTime.of(2020, Month.FEBRUARY, 22, 12, 00, 00);
        LocalDateTime disponible = LocalDateTime.of(2020, Month.APRIL, 19, 16, 00, 00);
        LocalDate dateAnniv1 = LocalDate.of(1993, Month.MARCH, 28);
        LocalDate dateAnniv2 = LocalDate.of(1991, Month.JANUARY, 22);
        Utilisateur utilisateur1 = new Utilisateur(1, "Helene", dateAnniv1);
        Utilisateur utilisateur2 = new Utilisateur(2, "Thomas", dateAnniv2);
        Plat gateauChoco = new Plat(14, "Gateau chocolat", "dessert", "fait maison", 3.2F, creationGateau, disponible, utilisateur1);
        Plat galetteBretonne = new Plat(15, "galette bretonne", "repas", "fait maison", 9.90F, creationGalette, disponible, utilisateur2);
        List<Plat> listePlats = new ArrayList<Plat>();
        listePlats.add(gateauChoco);
        listePlats.add(galetteBretonne);

        when(platRepository.findAll()).thenReturn(listePlats);

        //terminer ce test
    }


}
