package fr.uga.l3miage.pc.prisonersdilemma.controller;

import fr.uga.l3miage.pc.prisonersdilemma.model.ChoiceMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class RoundController {

    private final SimpMessagingTemplate messagingTemplate;

    public RoundController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Méthode pour gérer les rounds
    @MessageMapping("/round")  // Le message envoyé à /app/round sera traité ici
    @SendTo("/topic/round")
    public String  handleRounds(String nbRound) {
        // Traitement du nombre de rounds, par exemple un simple calcul ou gestion

        // Envoie du résultat à tous les clients abonnés au topic /topic/results
        return nbRound;
    }


}