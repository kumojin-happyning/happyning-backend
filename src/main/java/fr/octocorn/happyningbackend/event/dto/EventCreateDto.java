package fr.octocorn.happyningbackend.event.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
public class EventCreateDto {
    @NotNull(message = "Merci de préciser le nom")
    private String name;
    @NotNull(message = "Merci de préciser la description")
    private String description;
    @NotNull(message = "Merci de préciser le fuseau horaire")
    private String timezone;
    @NotNull(message = "Merci de préciser la date de début")
    private LocalDateTime start;
    @NotNull(message = "Merci de préciser la date de fin")
    private LocalDateTime end;
}
