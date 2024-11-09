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

  id : number = 0;
  isOtherPlayerConnected : boolean = false;

  roundCount: number = 0 ;

  constructor(public websocketService: WebsocketService) {}

  ngOnInit(): void {
    // Connecter le client WebSocket au serveur dès que le composant est initialisé
    // Vérifier la connexion dans le servicea
    this.websocketService.getConnectionStatus().subscribe(status => {
      this.isConnected = status;
      console.log("dans le subscirbe du stauys jai : ",status);
      if(status){
        this.websocketService.sendMessage("/app/connected","");
      }
    });
    this.websocketService.getRoundObserver().subscribe(round => {
      this.roundCount = round;
    });

    this.websocketService.getidObserver().subscribe(id => {
        if(!this.id){
          this.id = id;
        }
        if(id > 1) {
          this.isOtherPlayerConnected = true;
        }
        console.log('Message reçu du topic connected:', id);
      });
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
  // Fonction pour envoyer le nombre de Roudns au serveur
  sendRound() {
    if(this.roundCount){
      this.websocketService.sendMessage('/app/round',this.roundCount.toString());
    }

  }

  // Fonction pour récupérer les messages


}
