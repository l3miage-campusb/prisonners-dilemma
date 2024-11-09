import { Injectable } from '@angular/core';
import { Client, IMessage } from '@stomp/stompjs'; // Assurez-vous d'importer Client et IMessage
import * as SockJS from 'sockjs-client';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private idSubject: BehaviorSubject<number> = new BehaviorSubject<number>(0);

  private roundSubject: BehaviorSubject<number> = new BehaviorSubject<number>(0);

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
    console.log("je fais jext tur");
    // Activation de la connexion STOMP
    this.stompClient.activate();
    this.stompClient.onConnect = (frame) => {
      console.log('Connected: peneeeeeeeeeeeeeeeeeeee' + frame);
      // S'abonner aux topics après la connexion
      this.stompClient.subscribe('/topic/results', (message: IMessage) => {
        console.log('Message reçu:', message.body);
      });

      this.stompClient.subscribe('/topic/round', (message: IMessage) => {
        console.log('Message reçu du topic round:', message.body);
        this.roundSubject.next(Number(message.body)); // Mettre à jour le nombre de rounds
      });

      this.stompClient.subscribe('/topic/connected', (message: IMessage) => {
        this.idSubject.next(Number(message.body)); // Mettre à jour le nombre de rounds
      });
      this.connectionStatus.next(true);
      this.stompClient.onDisconnect = () => {
        this.connectionStatus.next(false); // Met à jour l'état de la connexion
        console.log('Disconnected');
      };


    }
  }

    // Méthode pour envoyer un message
    sendMessage(destination: string, body: string): void {
      console.log("Envoi du message : " + destination + body);
      if(this.stompClient && this.stompClient.connected) {
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

  getRoundObserver(): Observable<number> {
    return this.roundSubject.asObservable();
  }
  getidObserver(): Observable<number> {
    return this.idSubject.asObservable();
  }
}
