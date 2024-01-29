package fr.octocorn.happyningbackend.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
class EventControllerTest {

    @MockBean
    private EventService eventService;

    @Autowired
    MockMvc mockMvc;
    private Event event;

    @BeforeEach
    public void setUp() {
        event = Event.builder()
                .name("test")
                .start(ZonedDateTime.parse("2021-10-10T10:00:00+02:00"))
                .end(ZonedDateTime.parse("2021-10-10T11:00:00+02:00"))
                .description("test")
                .build();
    }

    @Test
    void findAll() throws Exception {
        when(eventService.findAll()).thenReturn(List.of(event));

        mockMvc.perform(get("/api/events"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("test"))
                .andExpect(jsonPath("$[0].start").value("2021-10-10T08:00:00Z"))
                .andExpect(jsonPath("$[0].end").value("2021-10-10T09:00:00Z"))
                .andExpect(jsonPath("$[0].description").value("test"));

        verify(eventService).findAll();
    }

    @Test
    void save() throws Exception {

        String body = "{" +
                "  \"name\": \"test\"," +
                "  \"start\": \"2021-10-10T10:00:00+02:00\"," +
                "  \"end\": \"2021-10-10T11:00:00+02:00\"," +
                "  \"description\": \"test\"" +
                "}";

        when(eventService.save(any(Event.class))).thenReturn(event);

        mockMvc.perform(post("/api/events")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isOk());

        verify(eventService).save(any(Event.class));
    }


}