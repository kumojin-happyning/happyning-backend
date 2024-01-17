package fr.octocorn.happyningbackend.event.validation;

import fr.octocorn.happyningbackend.event.Event;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ChronologicalDatesValidator implements ConstraintValidator<ChronologicalDates, Event> {

    @Override
    public boolean isValid(Event event, ConstraintValidatorContext context) {
        return event.getStart().isBefore(event.getEnd());
    }
}