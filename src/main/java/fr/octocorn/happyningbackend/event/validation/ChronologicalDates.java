package fr.octocorn.happyningbackend.event.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Cette annotation permet de valider que la date de début d'un événement est bien avant la date de fin.
 * Elle peut être utilisée sur une classe qui représente un événement, ayant une date de début et une date de fin.
 */
@Constraint(validatedBy = ChronologicalDatesValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ChronologicalDates {
    String message() default "La date de début doit être avant la date de fin";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}