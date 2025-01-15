package tn.esprit.gamepi.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gamepi.entities.Strategy;
import tn.esprit.gamepi.repositories.StrategyRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StrategyService {

    @Autowired
    private StrategyRepository strategyRepository;

    public Strategy createStrategy(Strategy strategy) {
        return strategyRepository.save(strategy);
    }

    public List<Strategy> getAllStrategies() {
        return strategyRepository.findAll();
    }

    public Optional<Strategy> getStrategyById(Long id) {
        return strategyRepository.findById(id);
    }

    public Strategy updateStrategy(Long id, Strategy strategyDetails) {
        Strategy strategy = strategyRepository.findById(id).orElseThrow(() -> new RuntimeException("Strategy not found"));
        strategy.setName(strategyDetails.getName());
        strategy.setDescription(strategyDetails.getDescription());
        return strategyRepository.save(strategy);
    }

    public void deleteStrategy(Long id) {
        strategyRepository.deleteById(id);
    }
}
