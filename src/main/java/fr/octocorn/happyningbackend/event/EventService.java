package fr.octocorn.happyningbackend.event;

import fr.octocorn.happyningbackend.event.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event save(Event event) {
        verifyData(event);
        return eventRepository.save(event);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    private static void verifyData(Event event) {

        if (event.getName() == null) {
            throw new BadRequestException("Le nom est obligatoire");
        }

        if (event.getName().length() > 33) {
            throw new BadRequestException("Le nom ne doit pas dépasser 33 caractères");
        }

        if (event.getStart() == null) {
            throw new BadRequestException("La date de début est obligatoire");
        }

        if (event.getEnd() == null) {
            throw new BadRequestException("La date de fin est obligatoire");
        }

        if (event.getStart().isAfter(event.getEnd())) {
            throw new BadRequestException("La date de début doit être avant la date de fin");
        }
    }
}
