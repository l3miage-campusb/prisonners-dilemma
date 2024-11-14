package fr.uga.l3miage.pc.prisonersdilemma.controller;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ConnectedController {


    private int nbJoueur = 0;
    public ConnectedController(SimpMessagingTemplate messagingTemplate) {

    }

    // Méthode pour gérer les rounds
    @MessageMapping("/connected")  // Le message envoyé à /app/round sera traité ici
    @SendTo("/topic/connected")
    public String  handleConnected() {
        // Envoie du résultat à tous les clients abonnés au topic /topic/results
        nbJoueur++;
        return String.valueOf(nbJoueur);
    }


}