package fr.octocorn.happyningbackend.event;

import fr.octocorn.happyningbackend.event.exception.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.ZonedDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = EventService.class)
class EventServiceTest {

    private EventService eventService;

    @MockBean
    private EventRepository eventRepository;

    @BeforeEach
    void setUp() {
        eventService = new EventService(eventRepository);
    }

    @Test
    @DisplayName("Sauvegarder un événement")
    void testSave() {

        // Arrange
        Event eventMock = Event.builder()
                .name("test")
                .start(ZonedDateTime.parse("2021-10-10T10:00:00+02:00"))
                .end(ZonedDateTime.parse("2021-10-10T11:00:00+02:00"))
                .description("test")
                .build();

        // Act
        eventService.save(eventMock);

        // Assert
        verify(eventRepository).save(eventMock);
    }

    @ParameterizedTest
    @DisplayName("Ne pas sauvegarder un événement invalide")
    @MethodSource("provideInvalidEvents")
    void shouldNotSaveInvalidEvent(Event event) {

        // Act
        assertThrows(BadRequestException.class, () -> eventService.save(event));
    }

    private static Stream<Arguments> provideInvalidEvents() {
        return Stream.of(
                Arguments.of(Event.builder()
                        .name(null)
                        .start(ZonedDateTime.parse("2021-10-10T10:00:00+02:00"))
                        .end(ZonedDateTime.parse("2021-10-10T11:00:00+02:00"))
                        .description("test")
                        .build()),
                Arguments.of(Event.builder()
                        .name("test")
                        .start(null)
                        .end(ZonedDateTime.parse("2021-10-10T11:00:00+02:00"))
                        .description("test")
                        .build()),
                Arguments.of(Event.builder()
                        .name("Beaucoup plus que 33 caractères donc ça ne passera pas du tout !")
                        .start(null)
                        .end(ZonedDateTime.parse("2021-10-10T11:00:00+02:00"))
                        .description("test")
                        .build()),
                Arguments.of(Event.builder()
                        .name("test")
                        .start(ZonedDateTime.parse("2021-10-10T10:00:00+02:00"))
                        .end(null)
                        .description("test")
                        .build()),
                Arguments.of(Event.builder()
                        .name("test")
                        .start(ZonedDateTime.parse("2021-10-10T11:00:00+02:00"))
                        .end(ZonedDateTime.parse("2021-10-10T10:00:00+02:00"))
                        .description("test")
                        .build())
        );
    }

    @Test
    @DisplayName("Récupérer tous les événements")
    void testFindAll() {

        // Act
        eventService.findAll();

        // Assert
        verify(eventRepository).findAll();
    }
}