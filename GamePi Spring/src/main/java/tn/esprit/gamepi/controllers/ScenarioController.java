package tn.esprit.gamepi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gamepi.entities.Scenario;
import tn.esprit.gamepi.services.ScenarioService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/scenarios")
public class ScenarioController {

    @Autowired
    private ScenarioService scenarioService;

    @PostMapping("/add")
    public Scenario createScenario(@RequestBody Scenario scenario) {
        return scenarioService.createScenario(scenario);
    }

    @GetMapping("/all")
    public List<Scenario> getAllScenarios() {
        return scenarioService.getAllScenarios();
    }

    @GetMapping("/{id}")
    public Optional<Scenario> getScenarioById(@PathVariable Long id) {
        return scenarioService.getScenarioById(id);
    }

    @PutMapping("/update/{id}")
    public Scenario updateScenario(@PathVariable Long id, @RequestBody Scenario scenario) {
        return scenarioService.updateScenario(id, scenario);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteScenario(@PathVariable Long id) {
        scenarioService.deleteScenario(id);
    }
}
