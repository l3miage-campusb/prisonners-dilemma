package fr.uga.l3miage.pc.prisonersdilemma.controller;

import fr.uga.l3miage.pc.prisonersdilemma.model.ChoiceMessage;
import fr.uga.l3miage.pc.prisonersdilemma.model.ResultMessage;

import fr.uga.l3miage.pc.prisonersdilemma.service.GameService;
import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

    private final GameService gameService;

    private final SimpMessagingTemplate messagingTemplate;

    public GameController(SimpMessagingTemplate messagingTemplate, GameService gameService) {
        this.messagingTemplate = messagingTemplate;
        this.gameService = gameService;
    }

    // Méthode pour gérer les choix des joueurs
    @MessageMapping("/choice") // Reçoit les messages envoyés à /app/choice
    public void handleChoice(ChoiceMessage message) {
        // Logique pour gérer le choix des joueurs
        ResultMessage result = gameService.processChoice(message);
        //Le résultat est nul si on a aps encore recu les deux réponses
        if(result !=null){
            messagingTemplate.convertAndSend("/topic/result", result);
        }
    }




    // Exemple de trait ement des choix

}