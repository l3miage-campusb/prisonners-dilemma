import { Component, OnInit, OnDestroy } from '@angular/core';
import { WebsocketService } from '../../services/websocket.service'; // Assurez-vous que le chemin est correct

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.scss']
})
export class AccueilComponent implements OnInit, OnDestroy {
  // Variable pour l'état de la connexion
  isConnected: boolean = false;
  message: string = '';

  constructor(private websocketService: WebsocketService) {}

  ngOnInit(): void {
    // Connecter le client WebSocket au serveur dès que le composant est initialisé
    // Vérifier la connexion dans le servicea
    this.websocketService.getConnectionStatus().subscribe(status => {
      this.isConnected = status;
    });
    console.log("osef de l errue je toure ?");
  }

  ngOnDestroy(): void {
    // Assurez-vous de déconnecter le client WebSocket lorsqu'on quitte le composant
    this.websocketService.disconnect();
  }

  // Fonction pour envoyer un message
  sendMessage(): void {
    if (this.isConnected) {
      this.websocketService.sendMessage('/app/choice', 'Message de test');
      console.log('Message envoyé!');
    }
  }

  // Fonction pour récupérer les messages
  getMessages(): void {
    this.websocketService.getMessages().subscribe((message: string) => {
      this.message = message;
      console.log('Message reçu:', message);
    });
  }
}
