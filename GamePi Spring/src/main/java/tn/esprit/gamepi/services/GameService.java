package tn.esprit.gamepi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gamepi.entities.*;
import tn.esprit.gamepi.repositories.*;

import java.util.*;

@Service
public class GameService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScenarioRepository scenarioRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private StrategyRepository strategyRepository; // Added to fetch strategy details

    // Track game state (user's current progress)
    private Map<Long, GameSession> gameSessions = new HashMap<>();

    // Map linking scenarios to strategies
    private static final Map<Long, Long> SCENARIO_TO_STRATEGY_MAP = Map.of(
            3L, 4L,
            4L, 2L,
            5L, 5L,
            6L, 7L,
            7L, 6L,
            8L, 8L,
            11L, 11L
    );

    // Define balance changes for each scenario and news
    private static final Map<Long, Map<Integer, Map<ActionType, Integer>>> SCENARIO_BALANCE_CHANGES = Map.of(
            4L, Map.of(
                    1, Map.of(ActionType.SELL, 700, ActionType.BUY, -300),
                    2, Map.of(ActionType.SELL, 200, ActionType.BUY, -600),
                    3, Map.of(ActionType.SELL, 200, ActionType.BUY, -100)
            ),
            5L, Map.of(
                    1, Map.of(ActionType.SELL, 300, ActionType.BUY, -300),
                    2, Map.of(ActionType.SELL, 1000, ActionType.BUY, -500),
                    3, Map.of(ActionType.SELL, 1300, ActionType.BUY, -200)
            ),
            6L, Map.of(
                    1, Map.of(ActionType.SELL, 1000, ActionType.BUY, -200),
                    2, Map.of(ActionType.SELL, 1200, ActionType.BUY, -400),
                    3, Map.of(ActionType.SELL, 1500, ActionType.BUY, -400)
            ),
            7L, Map.of(
                    1, Map.of(ActionType.SELL, 2000, ActionType.BUY, -800),
                    2, Map.of(ActionType.SELL, 100, ActionType.BUY, -100),
                    3, Map.of(ActionType.SELL, 100, ActionType.BUY, -100)
            ),
            8L, Map.of(
                    1, Map.of(ActionType.SELL, 400, ActionType.BUY, -200),
                    2, Map.of(ActionType.SELL, 400, ActionType.BUY, -400),
                    3, Map.of(ActionType.SELL, 600, ActionType.BUY, -400)
            ),
            11L, Map.of(
                    1, Map.of(ActionType.SELL, 700, ActionType.BUY, -300),
                    2, Map.of(ActionType.SELL, 200, ActionType.BUY, -600),
                    3, Map.of(ActionType.SELL, 200, ActionType.BUY, -100)
            )

    );

    // Step 1: Start the game
    public Map<String, Object> startGame(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // Randomly select a scenario from a predefined set of scenario IDs
        List<Long> predefinedScenarioIds = Arrays.asList(4L, 5L, 6L, 7L, 8L, 11L); // Set of valid scenario IDs
        Long randomScenarioId = predefinedScenarioIds.get(new Random().nextInt(predefinedScenarioIds.size()));

        // Fetch the scenario based on the random ID
        Scenario scenario = scenarioRepository.findById(randomScenarioId)
                .orElseThrow(() -> new RuntimeException("Scenario not found"));

        // Fetch the news for this scenario
        List<News> newsList = newsRepository.findByScenarioId(randomScenarioId);

        // Initialize game session
        GameSession session = new GameSession(user, scenario, newsList);
        gameSessions.put(userId, session);

        // Return initial game state (username, balance, and starting date)
        Map<String, Object> response = new HashMap<>();
        response.put("username", user.getUsername());
        response.put("balance", user.getBalance());
        response.put("startingDate", scenario.getStartingDate());
        response.put("scenarioId", randomScenarioId);

        return response;
    }

    // Step 2: Show the first news and process decisions
    public Map<String, Object> proceedWithGame(Long userId) {

        GameSession session = null;
        Scenario currentScenarioId = session.getScenarioId;

        session = gameSessions.get(userId);
        if (session == null) {
            throw new RuntimeException("Game not started for user");
        }

        News currentNews = session.getNextNews();
        if (currentNews == null) {
            // If no more news, game is over
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Game over. Thanks for playing!");
            return response;
        }

        // Prepare response for the current news
        Map<String, Object> response = new HashMap<>();
        response.put("newsDate", currentNews.getDate());
        response.put("headline", currentNews.getHeadline());
        response.put("content", currentNews.getContent());
        response.put("scenarioId", currnt);

        if (session.isLastNews()) {
            response.put("message", "Game over. Thanks for playing!");
        } else {
            response.put("message", "Please make a decision: BUY, SELL, or HOLD");
        }

        return response;
    }

    // Step 3: Process decision for the current news
    public Map<String, Object> processDecision(Long userId, ActionType action) {
        GameSession session = gameSessions.get(userId);
        if (session == null) {
            throw new RuntimeException("Game not started for user");
        }

        News currentNews = session.getNextNews();
        if (currentNews == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Game over. Thanks for playing!");
            return response;
        }

        Long scenarioId = session.getScenario().getId();
        int newsIndex = session.getCurrentNewsIndex();

        // Update balance based on the decision
        double balanceChange = SCENARIO_BALANCE_CHANGES
                .getOrDefault(scenarioId, Collections.emptyMap())
                .getOrDefault(newsIndex, Collections.emptyMap())
                .getOrDefault(action, 0);
        session.updateBalance(balanceChange);

        // Prepare response
        Map<String, Object> response = new HashMap<>();
        response.put("newsDate", currentNews.getDate());
        response.put("headline", currentNews.getHeadline());
        response.put("content", currentNews.getContent());
        response.put("balance", session.getBalance());

        if (session.isLastNews()) {
            // Final outcome
            double finalBalance = session.getBalance();
            if (scenarioId == 4L && finalBalance < 1000) {
                response.put("message", "You're Broke! It Crashed!");
            } else if (scenarioId == 4L) {
                response.put("message", "You Escaped The Market Crash!");
            } else if (scenarioId == 5L && finalBalance == 0) {
                response.put("balance", 1000000);
                response.put("message", "You're Rich! It Flew Up!");
            } else if (scenarioId == 5L) {
                response.put("message", "You Missed Your Chance! You Could've Been Richer!");
            }
            // Add conditions for other scenarios
        } else {
            response.put("message", "Please type BUY, SELL, or HOLD to proceed.");
        }

        return response;
    }
}
