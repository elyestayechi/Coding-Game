package tn.esprit.gamepi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.gamepi.entities.Decision;


public interface DecisionRepository extends JpaRepository<Decision, Long> {
}
