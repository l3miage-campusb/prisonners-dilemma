package fr.uga.l3miage.pc.prisonersdilemma.controller;


import fr.uga.l3miage.pc.prisonersdilemma.model.LeaveMessage;
import fr.uga.l3miage.pc.prisonersdilemma.model.ResultMessage;
import fr.uga.l3miage.pc.prisonersdilemma.service.GameService;
import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class LeaveController {

    private final SimpMessagingTemplate messagingTemplate;

    private final GameService gameService;

    public LeaveController(SimpMessagingTemplate messagingTemplate, GameService gameService) {
        this.messagingTemplate = messagingTemplate;
        this.gameService = gameService;
    }


    @MessageMapping("/leave") // Reçoit les messages envoyés à /app/choice
    public void handleLeave(LeaveMessage message) {
        // Logique pour gérer le choix des joueurs

        // Si un joueur a deja fait son choix puis l'autre quitte, on doit envoyer le resultat a ce moment
        ResultMessage result = this.gameService.handleLeave(message);

        if(result != null) {
            messagingTemplate.convertAndSend("/topic/result", result);
        }


    }

}