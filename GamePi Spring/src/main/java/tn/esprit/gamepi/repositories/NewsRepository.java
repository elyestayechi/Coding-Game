package tn.esprit.gamepi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.gamepi.entities.News;

import java.util.List;


public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByScenarioId(Long scenarioId);
}

