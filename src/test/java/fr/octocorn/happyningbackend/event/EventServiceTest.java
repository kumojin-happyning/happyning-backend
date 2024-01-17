package fr.octocorn.happyningbackend.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
        Event eventMock = mock(Event.class);

        // Act
        eventService.save(eventMock);

        // Assert
        verify(eventRepository).save(eventMock);
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