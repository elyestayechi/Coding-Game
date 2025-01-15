package tn.esprit.gamepi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gamepi.entities.Decision;
import tn.esprit.gamepi.entities.Scenario;
import tn.esprit.gamepi.entities.User;
import tn.esprit.gamepi.services.DecisionService;
import tn.esprit.gamepi.services.ScenarioService;
import tn.esprit.gamepi.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/decisions")
public class DecisionController {

    @Autowired
    private DecisionService decisionService;

    @Autowired
    private UserService userService;

    @Autowired
    private ScenarioService scenarioService;

    @PostMapping("/add")
    public Decision createDecision(@RequestBody Decision decision) {
        // Fetch User and Scenario based on the provided IDs
        Optional<User> user = userService.getUserById(decision.getUser().getId());
        Optional<Scenario> scenario = scenarioService.getScenarioById(decision.getScenario().getId());

        if (user.isPresent() && scenario.isPresent()) {
            decision.setUser(user.get());
            decision.setScenario(scenario.get());
            return decisionService.createDecision(decision);
        } else {
            throw new RuntimeException("User or Scenario not found");
        }
    }

    @GetMapping("/all")
    public List<Decision> getAllDecisions() {
        return decisionService.getAllDecisions();
    }

    @GetMapping("/{id}")
    public Optional<Decision> getDecisionById(@PathVariable Long id) {
        return decisionService.getDecisionById(id);
    }

    @PutMapping("/update/{id}")
    public Decision updateDecision(@PathVariable Long id, @RequestBody Decision decision) {
        return decisionService.updateDecision(id, decision);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDecision(@PathVariable Long id) {
        decisionService.deleteDecision(id);
    }
}
