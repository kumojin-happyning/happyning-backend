package fr.octocorn.happyningbackend.event.dto;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class EventDto {
    private UUID id;
    private String name;
    private String description;
    private ZonedDateTime start;
    private ZonedDateTime end;
}
