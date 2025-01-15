package tn.esprit.gamepi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gamepi.entities.ActionType;
import tn.esprit.gamepi.services.GameService;

import java.util.Map;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "http://localhost:4200")
public class GameController {

    @Autowired
    private GameService gameService;

    // Step 1: Start the game for a user
    @PostMapping("/start/{userId}")
    public Map<String, Object> startGame(@PathVariable Long userId) {
        return gameService.startGame(userId);
    }

    // Step 2: Proceed to show the first news (after starting the game)
    @PostMapping("/proceed/{userId}")
    public Map<String, Object> proceedWithGame(@PathVariable Long userId) {
        return gameService.proceedWithGame(userId);
    }

    // Step 3: Process decision for the current news (BUY, SELL, or HOLD)
    @PostMapping("/decision")
    public Map<String, Object> makeDecision(
            @RequestParam Long userId,
            @RequestParam ActionType action) {
        return gameService.processDecision(userId, action);
    }
}
