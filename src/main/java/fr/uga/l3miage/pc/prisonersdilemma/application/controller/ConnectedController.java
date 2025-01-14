package fr.uga.l3miage.pc.prisonersdilemma.application.controller;


import fr.uga.l3miage.pc.prisonersdilemma.domain.service.GameService;
import fr.uga.l3miage.pc.prisonersdilemma.domain.service.IGameService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ConnectedController {


    private final IGameService gameService;
    public ConnectedController(SimpMessagingTemplate messagingTemplate, GameService gameService) {
        this.gameService = gameService;
    }

    // Méthode pour gérer les rounds
    @MessageMapping("/connected")  // Le message envoyé à /app/round sera traité ici
    @SendTo("/topic/connected")
    public String  handleConnected() {
        gameService.setNbJoueurController(gameService.getNbJoueurController()+1);
        return String.valueOf(gameService.getNbJoueurController());
    }


}