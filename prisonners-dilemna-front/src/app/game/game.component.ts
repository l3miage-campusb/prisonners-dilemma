import { Component, OnInit, OnDestroy } from '@angular/core';
import { WebsocketService } from '../../services/websocket.service';  // Assurez-vous que le chemin est correct
import { Router } from '@angular/router'; // Import du Router pour la navigation si nécessaire
import { ChoiceMessage } from '../models/choiceMessage';
import { Choice } from '../enums/choice.enum';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {
  // Variable pour stocker le choix de l'utilisateur
  playerChoice!: Choice ;
  roundCount : number = 0;
  originalRoundCount : number = 0;

  constructor(private websocketService: WebsocketService) {
    this.roundCount = this.websocketService.roundCount;
    this.originalRoundCount = this.websocketService.roundCount;
  }

  ngOnInit(): void {

  }

  // Fonction pour envoyer le choix "Coopérer"
  cooperer() {
    this.playerChoice = Choice.Cooperer;
    this.sendChoice();
  }

  // Fonction pour envoyer le choix "Trahir"
  trahir() {
    this.playerChoice = Choice.Trahir;
    this.sendChoice();
  }

  // Fonction pour envoyer le choix au serveur
   // Fonction pour envoyer le choix
   sendChoice() {
    const message: ChoiceMessage = {
      playerId: this.websocketService.id,
      choice: this.playerChoice
    }
    this.websocketService.sendMessage("/app/choice",JSON.stringify(message));
  }
}
