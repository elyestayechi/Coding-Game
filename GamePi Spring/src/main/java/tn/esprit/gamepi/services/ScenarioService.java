package tn.esprit.gamepi.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gamepi.entities.Scenario;
import tn.esprit.gamepi.repositories.ScenarioRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ScenarioService {

    @Autowired
    private ScenarioRepository scenarioRepository;

    public Scenario createScenario(Scenario scenario) {
        return scenarioRepository.save(scenario);
    }

    public List<Scenario> getAllScenarios() {
        return scenarioRepository.findAll();
    }

    public Optional<Scenario> getScenarioById(Long id) {
        return scenarioRepository.findById(id);
    }

    public Scenario updateScenario(Long id, Scenario scenarioDetails) {
        Scenario scenario = scenarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Scenario not found"));
        scenario.setTitle(scenarioDetails.getTitle());
        scenario.setDescription(scenarioDetails.getDescription());
        scenario.setStartingDate(scenarioDetails.getStartingDate());
        scenario.setEndingDate(scenarioDetails.getEndingDate());
        return scenarioRepository.save(scenario);
    }

    public void deleteScenario(Long id) {
        scenarioRepository.deleteById(id);
    }
}
