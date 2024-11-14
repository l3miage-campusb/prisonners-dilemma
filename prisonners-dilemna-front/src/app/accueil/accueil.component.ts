import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { WebsocketService } from '../../services/websocket.service'; 

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.scss']
})
export class AccueilComponent implements OnInit {
  // Variable pour l'état de la connexion
  isConnected: boolean = false;
  message: string = '';

  id: number = 0;
  isOtherPlayerConnected: boolean = false;

  roundCount: number = 0;

  constructor(public websocketService: WebsocketService, private router: Router) { }

  ngOnInit(): void {
    // Connecter le client WebSocket au serveur dès que le composant est initialisé
    // Vérifier la connexion dans le servicea
    this.websocketService.getConnectionStatus().subscribe(status => {
      this.isConnected = status;
      console.log("dans le subscirbe du stauys jai : ", status);
      if (status) {
        this.websocketService.sendMessage("/app/connected", "");
      }
    });

    this.websocketService.getidObserver().subscribe(id => {
      if (!this.id) {
        this.id = id;
        this.websocketService.id = id;
      }
      if (id > 1) {
        this.isOtherPlayerConnected = true;
      }
      console.log('Message reçu du topic connected:', id);
    });


    //Une fois que l'on sait que le nombre de rounds a été recus, on passe a la page de jeu
    this.websocketService.getRoundObserver().subscribe(() => {
      this.router.navigate(['/game']);
    });
  }

  // Fonction pour envoyer le nombre de Roudns au serveur
  sendRound() {
    this.websocketService.sendMessage('/app/round', this.roundCount.toString());
  }

  // Fonction pour récupérer les messages


}
