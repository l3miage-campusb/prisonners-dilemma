import { Injectable } from '@angular/core';
import { Client, IMessage } from '@stomp/stompjs'; // Assurez-vous d'importer Client et IMessage
import * as SockJS from 'sockjs-client';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  private stompClient: Client;
  private connectionStatus: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor() {
    this.stompClient = new Client({
      webSocketFactory: () => new SockJS('http://localhost:8080/server'),
      connectHeaders: {
        login: 'user',
        passcode: 'password'
      },
      debug: (str) => console.log(str),
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
    });

    this.stompClient.onConnect = (frame) => {
      console.log('Connected: peneeeeeeeeeeeeeeeeeeee' + frame);
      this.connectionStatus.next(true);  // Met à jour l'état de la connexion

      // S'abonner aux topics après la connexion
      this.stompClient.subscribe('/topic/results', (message: IMessage) => {
        console.log('Message reçu:', message.body);
      });

      this.stompClient.subscribe('/topic/round', (message: IMessage) => {
        console.log('Message reçu du topic round:', message.body);
      });
    };

    this.stompClient.onDisconnect = () => {
      this.connectionStatus.next(false); // Met à jour l'état de la connexion
      console.log('Disconnected');
    };

    // Activation de la connexion STOMP
    this.stompClient.activate();
  }

  // Méthode pour envoyer un message
  sendMessage(destination: string, body: string): void {
    console.log("Envoi du message...");
    if (this.stompClient && this.stompClient.connected) {
      // Utilisez publish() au lieu de send()
      this.stompClient.publish({ destination: destination, body: body });
      console.log('Message envoyé à:', destination);
    } else {
      console.log("Le client n'est pas encore connecté.");
    }
  }

  // Méthode pour déconnecter
  disconnect(): void {
    if (this.stompClient && this.stompClient.connected) {
      this.stompClient.deactivate();
      console.log('Déconnecté');
      this.connectionStatus.next(false);
    } else {
      console.log("Le client n'est pas connecté.");
    }
  }

  // Obtenir l'état de la connexion
  getConnectionStatus(): Observable<boolean> {
    return this.connectionStatus.asObservable();
  }
}
