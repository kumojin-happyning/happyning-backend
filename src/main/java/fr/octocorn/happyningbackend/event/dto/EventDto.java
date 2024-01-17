package fr.octocorn.happyningbackend.event.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class EventDto {
    private Integer id;
    private String name;
    private String description;
    private String timezone;
    private ZonedDateTime start;
    private ZonedDateTime end;
}
