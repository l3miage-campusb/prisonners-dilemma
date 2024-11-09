import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccueilComponent } from './accueil/accueil.component';
import { GameComponent } from './game/game.component';

const routes: Routes = [
  {
    path: '',  // La route vide signifie l'accueil
    component: AccueilComponent  // Composant d'accueil
  },
  {
    path: 'game',  // La route vide signifie l'accueil
    component: GameComponent  // Composant d'accueil
  },
  // Vous pouvez ajouter d'autres routes ici pour d'autres pages de votre application
  //{
  //  path: 'other-page',
  //  component: OtherPageComponent  // Remplacez par vos autres composants si nécessaire
  //},
  // Route de secours pour les URL non reconnues
  {
    path: '**',
    redirectTo: ''  // Redirige vers la page d'accueil si aucune route n'est trouvée
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
