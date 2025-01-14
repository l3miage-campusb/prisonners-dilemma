package fr.uga.l3miage.pc.prisonersdilemma.controller;


import fr.uga.l3miage.pc.prisonersdilemma.service.GameService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ConnectedController {


    private final GameService gameService;
    public ConnectedController(GameService gameService) {
        this.gameService = gameService;
    }

    // Méthode pour gérer les rounds
    @MessageMapping("/connected")  // Le message envoyé à /app/round sera traité ici
    @SendTo("/topic/connected")
    public String  handleConnected() {
        // Envoie du résultat à tous les clients abonnés au topic /topic/results
        gameService.setNbJoueurController(gameService.getNbJoueurController()+1);
        return String.valueOf(gameService.getNbJoueurController());
    }


}