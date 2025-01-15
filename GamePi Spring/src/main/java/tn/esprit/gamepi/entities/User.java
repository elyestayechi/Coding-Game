package tn.esprit.gamepi.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor // Lombok will generate the no-argument constructor
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NonNull
    @Column(nullable = false, unique = true)
    String username;

    double balance = 10000.0; // Starting balance
    double totalProfitLoss = 0.0; // Tracks performance

    // Make the relationships private
    @ManyToMany
    @JoinTable(
            name = "user_scenario",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "scenario_id")
    )
    private Set<Scenario> scenarios = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Decision> decisions = new HashSet<>();

    // Getters and Setters
    public Set<Scenario> getScenarios() {
        return scenarios;
    }

    public void setScenarios(Set<Scenario> scenarios) {
        this.scenarios = scenarios;
    }

    public Set<Decision> getDecisions() {
        return decisions;
    }

    public void setDecisions(Set<Decision> decisions) {
        this.decisions = decisions;
    }
}
