package fr.uga.l3miage.pc.prisonersdilemma.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class WebSocketHandler extends TextWebSocketHandler {
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // Code pour gérer la connexion d’un nouveau joueur
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        // Code pour gérer la réception d'un message
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        // Code pour gérer la déconnexion d’un joueur
    }
}
