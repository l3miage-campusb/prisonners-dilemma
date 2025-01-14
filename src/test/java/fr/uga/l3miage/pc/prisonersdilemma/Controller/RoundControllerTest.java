package fr.uga.l3miage.pc.prisonersdilemma.Controller;

import fr.uga.l3miage.pc.prisonersdilemma.application.controller.RoundController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RoundControllerTest {

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @InjectMocks
    private RoundController roundController;

    @Test
    void testHandleRounds() {
        // Simulation de l'envoi d'un nombre de rounds en entrée
        String nbRound = "5";

        // Appel de la méthode handleRounds
        String result = roundController.handleRounds(nbRound);

        // Vérification que la méthode retourne bien la valeur passée en entrée
        assertEquals(nbRound, result, "La méthode handleRounds devrait retourner le nombre de rounds envoyé");
    }
}
