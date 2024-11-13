package fr.uga.l3miage.pc.prisonersdilemma.controller;


import fr.uga.l3miage.pc.prisonersdilemma.model.strategies.Strategy;
import fr.uga.l3miage.pc.prisonersdilemma.service.GameService;
import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class LeaveController {

    private final SimpMessagingTemplate messagingTemplate;

    private GameService gameService = new GameService();

    public LeaveController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }


    @MessageMapping("/leave") // Reçoit les messages envoyés à /app/choice
    public void handleLeave(Strategy strategie) {
        // Logique pour gérer le choix des joueurs
        this.gameService.setStrategie(strategie);
    }

}