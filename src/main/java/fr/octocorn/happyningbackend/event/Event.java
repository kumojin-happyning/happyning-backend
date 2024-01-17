package fr.octocorn.happyningbackend.event;

import fr.octocorn.happyningbackend.event.validation.ChronologicalDates;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "event")
@ChronologicalDates
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 32)
    @NotNull(message = "Le nom de l'événement ne peut pas être nul")
    @Size(min = 3, max = 32, message = "Le nom de l'événement doit faire entre 3 et 32 caractères")
    String name;

    @Column(nullable = false)
    @NotNull(message = "La description de l'événement ne peut pas être nulle")
    @Size(min = 3, max = 255, message = "La description de l'événement doit faire entre 3 et 255 caractères")
    String description;

    @Column(nullable = false)
    @NotNull(message = "Le lieu de l'événement ne peut pas être nul")
    ZonedDateTime start;

    @Column(nullable = false)
    @NotNull(message = "Le lieu de l'événement ne peut pas être nul")
    ZonedDateTime end;

    @Column(nullable = false)
    @NotNull(message = "Le fuseau horaire de l'événement ne peut pas être nul")
    String timezone;
}
