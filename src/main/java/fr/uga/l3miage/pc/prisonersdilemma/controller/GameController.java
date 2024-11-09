package fr.uga.l3miage.pc.prisonersdilemma.controller;

import fr.uga.l3miage.pc.prisonersdilemma.model.ChoiceMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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
        messagingTemplate.convertAndSend("/topic/results", "peneeeeeeeee");
    }

    // Méthode pour gérer les rounds
    @MessageMapping("/round")  // Le message envoyé à /app/round sera traité ici
    @SendTo("/topic/round")
    public String  handleRounds(String nbRound) {

        // Traitement du nombre de rounds, par exemple un simple calcul ou gestion

        // Envoie du résultat à tous les clients abonnés au topic /topic/results
        return nbRound;
    }

    // Exemple de trait ement des choix
    private String processChoices(ChoiceMessage message) {
        // Logique pour traiter les choix et retourner le résultat
        return "Résultat pour le choix de " + message.getPlayerId() + ": " + message.getChoice();
    }
}