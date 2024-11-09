import { Component, OnInit, OnDestroy } from '@angular/core';
import { WebsocketService } from '../../services/websocket.service';  // Assurez-vous que le chemin est correct
import { Router } from '@angular/router'; // Import du Router pour la navigation si nécessaire

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit, OnDestroy {
  // Variable pour stocker le choix de l'utilisateur
  playerChoice: string = '';
  roundCount : number = 0;
  originalRoundCount : number = 0;

  constructor(private websocketService: WebsocketService) {
    this.roundCount = this.websocketService.roundCount;
    this.originalRoundCount = this.websocketService.roundCount;
  }

  ngOnInit(): void {

  }

  ngOnDestroy(): void {
    // Nettoyage du service WebSocket si nécessaire
  }

  // Fonction pour envoyer le choix "Coopérer"
  cooperate() {
    this.playerChoice = 'cooperate';
    this.sendChoice();
  }

  // Fonction pour envoyer le choix "Trahir"
  betray() {
    this.playerChoice = 'betray';
    this.sendChoice();
  }

  // Fonction pour envoyer le choix au serveur
  sendChoice() {
    if (this.playerChoice) {
      console.log(`Envoi du choix: ${this.playerChoice}`);
      this.websocketService.sendMessage('/app/choice', this.playerChoice);  // Envoi du choix au serveur
    }
  }
}
