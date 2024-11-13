import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccueilComponent } from './accueil/accueil.component';
import { GameComponent } from './game/game.component';
import { EndGameComponent } from './end-game/end-game.component';

const routes: Routes = [
  {
    path: '',
    component: AccueilComponent
  },
  {
    path: 'game',
    component: GameComponent
  },
  { path: 'end-game', component: EndGameComponent },
  // Route de secours pour les URL non reconnues
  {
    path: '**',
    redirectTo: ''  
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
