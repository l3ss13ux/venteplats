package com.helene.venteplats.dtoTest;

import com.helene.venteplats.dto.CreationUtilisateurDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CreationUtilisateurDTOTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldFailValidation_whenNomIsNull() {
        LocalDate anniv = LocalDate.of(1952, Month.MAY,13);
        CreationUtilisateurDTO creationUtilisateurDTO = new CreationUtilisateurDTO(null, anniv);

        Set<ConstraintViolation<CreationUtilisateurDTO>> constraintViolations = validator.validate(creationUtilisateurDTO);
        assertEquals(1, constraintViolations.size());
        assertEquals("ne doit pas être vide", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void shouldFailValidation_whenNomSizeIsInvalid() {
        LocalDate anniv = LocalDate.of(1952, Month.MAY, 13);
        String nom = "h";
        CreationUtilisateurDTO creationUtilisateurDTO = new CreationUtilisateurDTO(nom, anniv);

        Set<ConstraintViolation<CreationUtilisateurDTO>> constraintViolations = validator.validate(creationUtilisateurDTO);
        assertEquals(1,constraintViolations.size());
        assertEquals("entre 3 et 27 caractères",constraintViolations.iterator().next().getMessage());
    }

}
