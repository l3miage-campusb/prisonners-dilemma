package fr.uga.l3miage.pc.prisonersdilemma.Controller;

import fr.uga.l3miage.pc.Choice;
import fr.uga.l3miage.pc.prisonersdilemma.controller.GameController;
import fr.uga.l3miage.pc.prisonersdilemma.model.ChoiceMessage;
import fr.uga.l3miage.pc.prisonersdilemma.model.ResultMessage;
import fr.uga.l3miage.pc.prisonersdilemma.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
class GameControllerTest {

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @Mock
    private GameService gameService;

    @InjectMocks
    private GameController gameController;



    @Test
    void testHandleChoice_withSimplifiedMock() {
        // Utilisation de matchers pour tous les arguments

        when(gameService.processChoice(any(ChoiceMessage.class))).thenReturn(new ResultMessage());

        // Exécution du test
        gameController.handleChoice(new ChoiceMessage("2", Choice.COOPERER));

        // Vérification de l'interaction
        verify(messagingTemplate, times(1)).convertAndSend(eq("/topic/result"), any(ResultMessage.class));
    }


    @Test
    void testHandleChoice_withoutResult() {
        ChoiceMessage choiceMessage = new ChoiceMessage("2", Choice.COOPERER);

        when(gameService.processChoice(choiceMessage)).thenReturn(null);

        gameController.handleChoice(choiceMessage);

        verify(messagingTemplate, never()).convertAndSend(anyString(), Optional.ofNullable(any()));
    }
}
