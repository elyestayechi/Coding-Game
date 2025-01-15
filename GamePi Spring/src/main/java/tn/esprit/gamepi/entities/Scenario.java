package tn.esprit.gamepi.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor // Generates the default no-argument constructor
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Scenario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NonNull
    @Column(nullable = false)
    String title;

    @Column(length = 500)
    String description;

    LocalDate startingDate;
    LocalDate endingDate;

    String historicalOutcome;
    String asset;
    String correctStrategy;

    // Make relationships private
    @ManyToMany(mappedBy = "scenarios")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "scenario")
    private Set<News> news = new HashSet<>();

    @OneToMany(mappedBy = "scenario")
    private Set<Decision> decisions = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "scenario_strategy",
            joinColumns = @JoinColumn(name = "scenario_id"),
            inverseJoinColumns = @JoinColumn(name = "strategy_id")
    )
    private Set<Strategy> strategies = new HashSet<>();

    // Getter and Setter methods
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<News> getNews() {
        return news;
    }

    public void setNews(Set<News> news) {
        this.news = news;
    }

    public Set<Decision> getDecisions() {
        return decisions;
    }

    public void setDecisions(Set<Decision> decisions) {
        this.decisions = decisions;
    }

    public Set<Strategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(Set<Strategy> strategies) {
        this.strategies = strategies;
    }
}
