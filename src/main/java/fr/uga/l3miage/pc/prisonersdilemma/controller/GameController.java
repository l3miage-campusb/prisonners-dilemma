package fr.uga.l3miage.pc.prisonersdilemma.controller;

import fr.uga.l3miage.pc.prisonersdilemma.model.ChoiceMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

    private final SimpMessagingTemplate messagingTemplate;

    public GameController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Méthode pour gérer les choix des joueurs
    @MessageMapping("/choice") // Reçoit les messages envoyés à /app/choice
    public void handleChoice(ChoiceMessage message) {
        // Logique pour gérer le choix des joueurs
        String result = processChoices(message);

        // Envoyer le résultat à tous les clients abonnés au topic /topic/results
        messagingTemplate.convertAndSend("/topic/results", result);
    }

    // Exemple de traitement des choix
    private String processChoices(ChoiceMessage message) {
        // Logique pour traiter les choix et retourner le résultat
        return "Résultat pour le choix de " + message.getPlayerId() + ": " + message.getChoice();
    }
}