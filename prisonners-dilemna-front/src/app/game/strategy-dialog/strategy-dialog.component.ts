import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Strategy } from 'src/app/enums/startegy.enum';

@Component({
  selector: 'app-strategy-dialog',
  templateUrl: './strategy-dialog.component.html',
  styleUrls: ['./strategy-dialog.component.scss']
})
export class StrategyDialogComponent {
  strategies = Object.values(Strategy);

  constructor(
    public dialogRef: MatDialogRef<StrategyDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  chooseStrategy(strategy: string) {
    this.dialogRef.close(strategy); 
  }
}
