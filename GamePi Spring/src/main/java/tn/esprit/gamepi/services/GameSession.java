package tn.esprit.gamepi.services;

import tn.esprit.gamepi.entities.News;
import tn.esprit.gamepi.entities.Scenario;
import tn.esprit.gamepi.entities.User;

import java.util.List;

public class GameSession {

    private final User user;
    private final Scenario scenario;
    private final List<News> newsList;
    private double balance; // Change balance to non-final so it can be updated
    private int currentNewsIndex;

    public GameSession(User user, Scenario scenario, List<News> newsList) {
        this.user = user;
        this.scenario = scenario;
        this.newsList = newsList;
        this.currentNewsIndex = 0;
        this.balance = user.getBalance(); // Initialize balance from user
    }

    public User getUser() {
        return user;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public News getNextNews() {
        if (currentNewsIndex < newsList.size()) {
            return newsList.get(currentNewsIndex++);
        }
        return null; // No more news
    }

    public boolean isLastNews() {
        return currentNewsIndex == newsList.size();
    }

    public double getBalance() {
        return balance; // Return current session balance
    }

    public void updateBalance(double amount) {
        this.balance += amount; // Adjust balance by the given amount
    }

    public int getCurrentNewsIndex() {
        return currentNewsIndex; // Return the current news index
    }
}
