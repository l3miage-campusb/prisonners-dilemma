import { Injectable } from '@angular/core';
import { Client, IMessage } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  private stompClient: Client;
  private connectionStatus: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor() {
    // Initialisation du client STOMP
    this.stompClient = new Client({
      // Utilisez 'webSocketFactory' pour une compatibilité avec SockJS
      webSocketFactory: () => new SockJS('http://localhost:8080/server') as any, // Casting en `any` pour éviter l'erreur de type
      connectHeaders: {
        login: 'user',
        passcode: 'password'
      },
      debug: (str) => console.log(str),
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000
    });

    // Définir le comportement après connexion
    this.stompClient.onConnect = (frame) => {
      console.log('Connected: ' + frame);
      this.connectionStatus.next(true);  // Mettre à jour l'état de la connexion

      // S'abonner au topic
      this.stompClient.subscribe('/topic/results', (message: IMessage) => {
        console.log('Message reçu:', message.body);
      });
    };

    // Activation de la connexion STOMP
    this.stompClient.activate();
  }

  // Méthode pour envoyer un message
  sendMessage(destination: string, body: string): void {
    if (this.stompClient && this.stompClient.connected) {
      this.stompClient.publish({ destination: destination, body: body });
    }
  }

  // Méthode pour déconnecter
  disconnect(): void {
    if (this.stompClient && this.stompClient.connected) {
      this.stompClient.deactivate();
      console.log('Disconnected');
      this.connectionStatus.next(false); // Mettre à jour l'état de la connexion
    }
  }

  // Obtenir l'état de la connexion
  getConnectionStatus(): Observable<boolean> {
    return this.connectionStatus.asObservable();
  }

  // Obtenir les messages
  getMessages(): Observable<string> {
    return new Observable<string>((observer) => {
      this.stompClient.subscribe('/topic/results', (message: IMessage) => {
        observer.next(message.body);  // Envoi du message à l'observateur
      });
    });
  }
}
