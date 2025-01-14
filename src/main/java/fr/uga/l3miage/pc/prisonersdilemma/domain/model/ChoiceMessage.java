package fr.uga.l3miage.pc.prisonersdilemma.domain.model;

public class ChoiceMessage {
    private String playerId;
    private Choice choice; // Trahir ou Cooperer

    // Constructeur, getters et setters
    public ChoiceMessage() {}

    public ChoiceMessage(String playerId, Choice choice) {
        this.playerId = playerId;
        this.choice = choice;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public Choice getChoice() {
        return choice;
    }

}