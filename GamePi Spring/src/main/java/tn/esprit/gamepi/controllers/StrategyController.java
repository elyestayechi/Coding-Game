package tn.esprit.gamepi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gamepi.entities.Strategy;
import tn.esprit.gamepi.services.StrategyService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/strategies")
public class StrategyController {

    @Autowired
    private StrategyService strategyService;

    @PostMapping("/add")
    public Strategy createStrategy(@RequestBody Strategy strategy) {
        return strategyService.createStrategy(strategy);
    }

    @GetMapping("/all")
    public List<Strategy> getAllStrategies() {
        return strategyService.getAllStrategies();
    }

    @GetMapping("/{id}")
    public Optional<Strategy> getStrategyById(@PathVariable Long id) {
        return strategyService.getStrategyById(id);
    }

    @PutMapping("/update/{id}")
    public Strategy updateStrategy(@PathVariable Long id, @RequestBody Strategy strategy) {
        return strategyService.updateStrategy(id, strategy);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStrategy(@PathVariable Long id) {
        strategyService.deleteStrategy(id);
    }
}
