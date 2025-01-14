import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Strategy } from 'src/app/enums/startegy.enum';

@Component({
  selector: 'app-strategy-dialog',
  templateUrl: './strategy-dialog.component.html',
  styleUrls: ['./strategy-dialog.component.scss']
})
export class StrategyDialogComponent {
 // Liste des stratégies non fonctionnelles (celles partagées, "la chaine" marche mais les fonctions dedans non)
 disabledStrategies: string[] = [
  'ADAPTATIVE',
  'ALWAYSBETRAY',
  'ALWAYSCOOPERATE',
  'GRADUALSTRATEGY',
  'NAIVEPEACEMAKER',
  'PAVLOV2',
  'PAVLORANDOM',
  'PEACEMAKER',
  'POLLSTERRANDOMBETRAY',
  'RANDOMSTRATEGY',
  'REPENTANTPOLLSTER',
  'RESENTFULSTRATEGY',
  'SOFTRESENTFUL',
  'TITFORTAT',
  'TITFORTATRANDOM',
  'TITFORTATSUSPICIOUS',
  'TITFORTWOTATS',
  'TITFORTWOTATSRANDOM'
];


  strategies = Object.values(Strategy);

  constructor(
    public dialogRef: MatDialogRef<StrategyDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  chooseStrategy(strategy: string) {
    this.dialogRef.close(strategy);
  }

  isDisabled(strategy: string): boolean {
    return this.disabledStrategies.includes(strategy);
  }
}
