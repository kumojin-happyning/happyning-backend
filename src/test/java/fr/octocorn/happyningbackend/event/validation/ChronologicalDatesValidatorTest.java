package fr.octocorn.happyningbackend.event.validation;

import fr.octocorn.happyningbackend.event.Event;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ChronologicalDatesValidatorTest {

    private ChronologicalDatesValidator validator;

    @Mock
    private ConstraintValidatorContext context;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        validator = new ChronologicalDatesValidator();
    }

    @Test
    @DisplayName(("L'évènement commence avant sa fin"))
    public void givenStartBeforeEnds_whenIsValid_thenIsValid() {
        Event event = new Event();
        event.setStart(ZonedDateTime.now());
        event.setEnd(ZonedDateTime.now().plusDays(1));

        assertTrue(validator.isValid(event, context));
    }

    @Test
    @DisplayName("L'évènement commence après sa fin")
    public void givenStartAfterEnds_whenIsValid_thenIsNotValid() {
        Event event = new Event();
        event.setStart(ZonedDateTime.now().plusDays(1));
        event.setEnd(ZonedDateTime.now());

        assertFalse(validator.isValid(event, context));
    }

    @Test
    @DisplayName("L'évènement commence en même temps qu'il finit")
    public void givenStartEqualsEnds_whenIsValid_thenIsNotValid() {
        Event event = new Event();
        event.setStart(ZonedDateTime.now());
        event.setEnd(ZonedDateTime.now());

        assertFalse(validator.isValid(event, context));
    }
}