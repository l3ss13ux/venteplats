package com.helene.venteplats.dtoTest;

import com.helene.venteplats.dto.CreationUtilisateurDTO;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestClass;


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

    @BeforeTestClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void nomIsNull() {
        LocalDate anniv = LocalDate.of(1952, Month.MAY,13);
        CreationUtilisateurDTO creationUtilisateurDTO = new CreationUtilisateurDTO(null, anniv);

        Set<ConstraintViolation<CreationUtilisateurDTO>> constraintViolations = validator.validate(creationUtilisateurDTO);
        assertEquals(1, constraintViolations.size());
        assertEquals("ne doit pas être null", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void nomSizeIsBad() {
        LocalDate anniv = LocalDate.of(1952, Month.MAY, 13);
        String nom = "h";
        CreationUtilisateurDTO creationUtilisateurDTO = new CreationUtilisateurDTO(nom, anniv);

        Set<ConstraintViolation<CreationUtilisateurDTO>> constraintViolations = validator.validate(creationUtilisateurDTO);
        assertEquals(1,constraintViolations.size());
        assertEquals("le nombre de caractères du nom est incorrect",constraintViolations.iterator().next().getMessage());
    }

}
