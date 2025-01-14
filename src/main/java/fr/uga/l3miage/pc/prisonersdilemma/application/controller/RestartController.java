package fr.uga.l3miage.pc.prisonersdilemma.application.controller;


import fr.uga.l3miage.pc.prisonersdilemma.domain.service.IGameService;
import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class RestartController {

    private final SimpMessagingTemplate messagingTemplate;

    private final IGameService gameService;

    public RestartController(SimpMessagingTemplate messagingTemplate, IGameService gameService) {
        this.messagingTemplate = messagingTemplate;
        this.gameService = gameService;
    }


    @MessageMapping("/restart")
    public void handleRestart() {

        gameService.redemarrerService();

        messagingTemplate.convertAndSend("/topic/restart", "ZOERAAAAA");

    }

}