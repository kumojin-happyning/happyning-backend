package fr.octocorn.happyningbackend.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.octocorn.happyningbackend.event.dto.EventDto;
import fr.octocorn.happyningbackend.event.exception.BadRequestException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin
public class EventController {
    private final EventService eventService;

    private final ObjectMapper objectMapper;

    public EventController(EventService eventService, ObjectMapper objectMapper) {
        this.eventService = eventService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<EventDto> findAll() {
        List<Event> events = eventService.findAll();
        return events.stream().map(event -> objectMapper.convertValue(event, EventDto.class)).toList();
    }

    @PostMapping
    public EventDto save(@RequestBody Event event) {

        Event eventToSave = eventService.save(event);

        return objectMapper.convertValue(eventToSave, EventDto.class);

    }


}
