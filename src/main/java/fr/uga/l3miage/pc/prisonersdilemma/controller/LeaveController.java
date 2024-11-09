package fr.uga.l3miage.pc.prisonersdilemma.controller;

import fr.uga.l3miage.pc.prisonersdilemma.model.ChoiceMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class LeaveController {

    private final SimpMessagingTemplate messagingTemplate;

    public LeaveController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Méthode pour gérer les rounds
    @MessageMapping("/leave")  // Le message envoyé à /app/round sera traité ici
    @SendTo("/topic/leave")
    public String  handleLeave(String leave) {

        // Envoie du résultat à tous les clients abonnés au topic /topic/leave
        return leave;
    }

}