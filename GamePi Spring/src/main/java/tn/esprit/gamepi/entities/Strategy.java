package tn.esprit.gamepi.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Strategy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NonNull
    @Column(nullable = false)
    String name;

    @Column(length = 1000)
    String description;

    // Make the relationship private
    @ManyToMany(mappedBy = "strategies")
    private Set<Scenario> scenarios = new HashSet<>();

    // Getter and Setter
    public Set<Scenario> getScenarios() {
        return scenarios;
    }

    public void setScenarios(Set<Scenario> scenarios) {
        this.scenarios = scenarios;
    }
}
