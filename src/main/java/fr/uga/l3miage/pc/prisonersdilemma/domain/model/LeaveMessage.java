package fr.uga.l3miage.pc.prisonersdilemma.domain.model;

import fr.uga.l3miage.pc.prisonersdilemma.domain.model.strategies.Strategy;

public class LeaveMessage {
    private int playerId; // Trahir ou Cooperer
    private Strategy strategy;

    

    // Constructeur, getters et setters
    public LeaveMessage() {}

    public LeaveMessage(Strategy strategy, int playerId) {
        this.strategy = strategy;
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public Strategy getStrategy() {
        return strategy;
    }



}