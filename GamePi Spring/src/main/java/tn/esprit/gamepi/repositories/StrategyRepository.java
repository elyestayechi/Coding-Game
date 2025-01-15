package tn.esprit.gamepi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.gamepi.entities.Strategy;


public interface StrategyRepository extends JpaRepository<Strategy, Long> {
}
