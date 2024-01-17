package fr.octocorn.happyningbackend.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.octocorn.happyningbackend.event.dto.EventCreateDto;
import fr.octocorn.happyningbackend.event.dto.EventDto;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/api/events")
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
    public Event save(@RequestBody EventCreateDto newEvent) {
        return eventService.save(
                eventFromEventCreateDto(newEvent)
        );
    }

    /**
     * Converti un EventCreateDto en Event en prenant en compte le fuseau horaire
     *
     * @param newEvent dto à convertir
     * @return l'Event
     */
    private static Event eventFromEventCreateDto(EventCreateDto newEvent) {
        Event event = new Event();
        ZoneId zoneId = ZoneId.of(newEvent.getTimezone());

        event.setName(newEvent.getName());
        event.setDescription(newEvent.getDescription());
        event.setStart(newEvent.getStart().atZone(zoneId));
        event.setEnd(newEvent.getEnd().atZone(zoneId));
        event.setTimezone(newEvent.getTimezone());
        return event;
    }
}
