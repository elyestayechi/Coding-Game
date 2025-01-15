package tn.esprit.gamepi.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor // Lombok will generate the no-argument constructor
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class News implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NonNull
    String headline;

    @Column(length = 1000)
    String content;

    LocalDate date;

    @ManyToOne
    @JoinColumn(name = "scenario_id")
    Scenario scenario;
}
