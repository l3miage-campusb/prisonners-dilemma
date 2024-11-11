import { Component, OnInit, OnDestroy } from '@angular/core';
import { WebsocketService } from '../../services/websocket.service';  // Assurez-vous que le chemin est correct
import { Router } from '@angular/router'; // Import du Router pour la navigation si nécessaire
import { ChoiceMessage } from '../models/choiceMessage';
import { Choice } from '../enums/choice.enum';
import { gameInfo } from '../models/gameInfo';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {
  // Variable pour stocker le choix de l'utilisateur
  playerChoice!: Choice ;
  choiceDone : boolean = false;
  gameInfo : gameInfo = {"roundCount" : 0, "scoreJ1" : 0, "scoreJ2" : 0,"resultat" : "" };
  originalRoundCount : number = 0;

  constructor(private websocketService: WebsocketService) {
    this.gameInfo.roundCount = this.websocketService.roundCount;
    this.originalRoundCount = this.websocketService.roundCount;
  }

  ngOnInit(): void {
    this.websocketService.getResultObserver().subscribe(result => {
      this.gameInfo.scoreJ1 = result.scoreJ1;
      this.gameInfo.scoreJ2 = result.scoreJ2;

      if(this.websocketService.id == 1){
        this.gameInfo.resultat = result.reponseJ1;
      }
      else if (this.websocketService.id == 2){
        this.gameInfo.resultat = result.reponseJ2;
      }
  })
}

  // Fonction pour envoyer le choix "Coopérer"
  cooperer() {
    this.choiceDone = true;
    this.playerChoice = Choice.Cooperer;
    this.sendChoice();
  }

  // Fonction pour envoyer le choix "Trahir"
  trahir() {
    this.choiceDone = true;
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

  continueGame(){
    this.choiceDone = false;
    this.gameInfo.roundCount --;
    this.gameInfo.resultat = "";
  }

  stopGame(){

  }

  isGameEnded(){
    return this.gameInfo.roundCount ==0;
  }
}
