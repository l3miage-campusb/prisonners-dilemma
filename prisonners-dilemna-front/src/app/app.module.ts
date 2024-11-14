import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AccueilComponent } from './accueil/accueil.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

// Angular Material modules
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { GameComponent } from './game/game.component';
import { CommonModule } from '@angular/common';
import { StrategyDialogComponent } from './game/strategy-dialog/strategy-dialog.component';
import { MatDialogModule } from '@angular/material/dialog';
import { EndGameComponent } from './end-game/end-game.component';

@NgModule({
  declarations: [
    AppComponent,
    AccueilComponent,
    GameComponent,
    StrategyDialogComponent,
    EndGameComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatButtonModule,
    MatFormFieldModule,
    FormsModule,
    MatDialogModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
