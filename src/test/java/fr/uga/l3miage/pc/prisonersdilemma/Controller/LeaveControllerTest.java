package fr.uga.l3miage.pc.prisonersdilemma.Controller;

import fr.uga.l3miage.pc.prisonersdilemma.application.controller.LeaveController;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.LeaveMessage;
import fr.uga.l3miage.pc.prisonersdilemma.domain.model.ResultMessage;
import fr.uga.l3miage.pc.prisonersdilemma.domain.service.GameService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
class LeaveControllerTest {

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @Mock
    private GameService gameService;

    @InjectMocks
    private LeaveController leaveController;

    @Test
    void testHandleLeave_withResult() {
        // Préparation du message et de la réponse
        LeaveMessage leaveMessage = new LeaveMessage();
        ResultMessage resultMessage = new ResultMessage();

        // Configuration du mock pour retourner un ResultMessage
        when(gameService.handleLeave(leaveMessage)).thenReturn(resultMessage);

        // Exécution de la méthode
        leaveController.handleLeave(leaveMessage);

        // Vérification de l'envoi du message de résultat
        verify(messagingTemplate, times(1)).convertAndSend("/topic/result", resultMessage);
    }

    @Test
    void testHandleLeave_withoutResult() {
        // Préparation du message
        LeaveMessage leaveMessage = new LeaveMessage();

        // Configuration du mock pour retourner null
        when(gameService.handleLeave(leaveMessage)).thenReturn(null);

        // Exécution de la méthode
        leaveController.handleLeave(leaveMessage);

        // Vérification qu'aucun message n'est envoyé lorsque le résultat est null
        verify(messagingTemplate, never()).convertAndSend(anyString(), Optional.ofNullable(any()));
    }
}
