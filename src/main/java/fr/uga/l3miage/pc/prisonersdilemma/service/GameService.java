package fr.uga.l3miage.pc.prisonersdilemma.service;

import fr.uga.l3miage.pc.prisonersdilemma.model.ChoiceMessage;
import fr.uga.l3miage.pc.prisonersdilemma.model.GameState;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    public GameState processChoice(ChoiceMessage message) {
        // Logique pour traiter les choix des joueurs
        GameState gameState = new GameState();
        // Initialiser gameState selon les choix
        return gameState;
    }
}