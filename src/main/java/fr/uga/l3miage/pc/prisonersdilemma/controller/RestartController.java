package fr.uga.l3miage.pc.prisonersdilemma.controller;

import fr.uga.l3miage.pc.prisonersdilemma.model.LeaveMessage;
import fr.uga.l3miage.pc.prisonersdilemma.model.ResultMessage;
import fr.uga.l3miage.pc.prisonersdilemma.service.GameService;


import fr.uga.l3miage.pc.prisonersdilemma.model.LeaveMessage;
import fr.uga.l3miage.pc.prisonersdilemma.model.ResultMessage;
import fr.uga.l3miage.pc.prisonersdilemma.service.GameService;
import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class RestartController {

    private final SimpMessagingTemplate messagingTemplate;

    private final GameService gameService;

    public RestartController(SimpMessagingTemplate messagingTemplate, GameService gameService) {
        this.messagingTemplate = messagingTemplate;
        this.gameService = gameService;
    }


    @MessageMapping("/restart")
    public void handleRestart(String message) {

        System.out.println(message);
        gameService.redemarrerService();
        messagingTemplate.convertAndSend("/topic/restart", "ZOERAAAAA");

    }

}