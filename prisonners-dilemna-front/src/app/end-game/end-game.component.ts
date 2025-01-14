import { Component } from '@angular/core';

@Component({
  selector: 'app-end-game',
  templateUrl: './end-game.component.html',
  styleUrls: ['./end-game.component.scss']
})
export class EndGameComponent {


  openPage(){
    window.open('https://candid-liger-6e7a01.netlify.app/', '_blank');
}
}
