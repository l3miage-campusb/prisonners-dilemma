package fr.uga.l3miage.pc.prisonersdilemma.model;

public class ChoiceMessage {
    private String playerId;
    private String choice; // Trahir ou Cooperer

    // Constructeur, getters et setters
    public ChoiceMessage() {}

    public ChoiceMessage(String playerId, String choice) {
        this.playerId = playerId;
        this.choice = choice;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getChoice() {
        return choice;
    }

}