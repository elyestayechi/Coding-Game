package tn.esprit.gamepi.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gamepi.entities.Decision;
import tn.esprit.gamepi.repositories.DecisionRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DecisionService {

    @Autowired
    private DecisionRepository decisionRepository;

    public Decision createDecision(Decision decision) {
        return decisionRepository.save(decision);
    }

    public List<Decision> getAllDecisions() {
        return decisionRepository.findAll();
    }

    public Optional<Decision> getDecisionById(Long id) {
        return decisionRepository.findById(id);
    }

    public Decision updateDecision(Long id, Decision decisionDetails) {
        Decision decision = decisionRepository.findById(id).orElseThrow(() -> new RuntimeException("Decision not found"));
        decision.setAction(decisionDetails.getAction());
        decision.setDate(decisionDetails.getDate());
        decision.setProfitOrLoss(decisionDetails.getProfitOrLoss());
        return decisionRepository.save(decision);
    }

    public void deleteDecision(Long id) {
        decisionRepository.deleteById(id);
    }
}
