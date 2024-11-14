package fr.uga.l3miage.pc.prisonersdilemma.model;

import fr.uga.l3miage.pc.prisonersdilemma.model.strategies.Strategy;

public class LeaveMessage {
    private Strategy strategie;
    private int playerId; // Trahir ou Cooperer

    // Constructeur, getters et setters
    public LeaveMessage() {}

    public LeaveMessage(Strategy strategie, int playerId) {
        this.strategie = strategie;
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public Strategy getStrategy() {
        return this.strategie;
    }



}