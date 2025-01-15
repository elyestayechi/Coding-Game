package tn.esprit.gamepi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.gamepi.entities.Scenario;


public interface ScenarioRepository extends JpaRepository<Scenario, Long> {
}
