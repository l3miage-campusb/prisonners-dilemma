package fr.uga.l3miage.pc.prisonersdilemma.Controller;

import fr.uga.l3miage.pc.prisonersdilemma.controller.ConnectedController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ConnectedControllerTest {

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @InjectMocks
    private ConnectedController connectedController = new ConnectedController(messagingTemplate);;


    @Test
    void testHandleConnected() {

        String result1 = connectedController.handleConnected();
        String result2 = connectedController.handleConnected();


        assertEquals("1", result1);
        assertEquals("2", result2);
    }
}
