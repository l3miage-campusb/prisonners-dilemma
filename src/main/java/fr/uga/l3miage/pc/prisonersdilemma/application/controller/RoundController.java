package fr.uga.l3miage.pc.prisonersdilemma.application.controller;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class RoundController {

    public RoundController(SimpMessagingTemplate messagingTemplate) {

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