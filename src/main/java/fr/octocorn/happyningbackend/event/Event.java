package fr.octocorn.happyningbackend.event;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "event")
@Builder
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(nullable = false, length = 32)
    @Size(max = 32)
    String name;

    String description;

    @Column(nullable = false, columnDefinition = "DATETIME")
    @NotNull(message = "Le lieu de l'événement ne peut pas être nul")
    ZonedDateTime start;

    @Column(nullable = false, columnDefinition = "DATETIME")
    @NotNull(message = "Le lieu de l'événement ne peut pas être nul")
    ZonedDateTime end;
}
