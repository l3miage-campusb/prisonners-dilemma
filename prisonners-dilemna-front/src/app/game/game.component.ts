
import { Component, OnInit } from '@angular/core';
import { WebsocketService } from '../../services/websocket.service';

import { ChoiceMessage } from '../models/choiceMessage';
import { LeaveMessage } from '../models/leaveMessage';
import { Choice } from '../enums/choice.enum';
import { gameInfo } from '../models/gameInfo';
import { MatDialog } from '@angular/material/dialog';
import { StrategyDialogComponent } from './strategy-dialog/strategy-dialog.component';
import { Strategy } from '../enums/startegy.enum';

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

  constructor(private websocketService: WebsocketService, public dialog: MatDialog) {
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
   sendChoice() {
    this.gameInfo.roundCount --;
    const message: ChoiceMessage = {
      playerId: this.websocketService.id,
      choice: this.playerChoice
    }
    this.websocketService.sendMessage("/app/choice",JSON.stringify(message));
  }

  continueGame(){
    this.choiceDone = false;
    this.gameInfo.resultat = "";
  }

  stopGame() {
    const dialogRef = this.dialog.open(StrategyDialogComponent);

    dialogRef.afterClosed().subscribe((result : Strategy) => {
      if (result) {
        const message: LeaveMessage = {
          playerId: this.websocketService.id,
          strategy: result
        }

        this.websocketService.sendMessage('/app/leave',JSON.stringify(message));
        this.websocketService.disconnect();

      }
    });
  }

  isGameEnded(){
    return this.gameInfo.roundCount ==0;
  }
}
