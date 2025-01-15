// sample-page.component.ts
import { Component } from '@angular/core';
import { GameService } from '../../../services/game.service';  // Ensure correct import path

@Component({
  selector: 'app-sample-page',
  templateUrl: './sample-page.component.html',
})
export class AppSamplePageComponent {
  userId = 1; // Example user ID
  gameState: any = null;
  currentNews: any = null;
  decisionMessage: string = '';

  constructor(private gameService: GameService) {}

  // Step 1: Start the game
  startGame(): void {
    this.gameService.startGame(this.userId).subscribe((response) => {
      this.gameState = response;
      console.log('Game started:', response);
    });
  }

  // Step 2: Proceed with the game
  proceedWithGame(): void {
    this.gameService.proceedWithGame(this.userId).subscribe((response) => {
      this.currentNews = response;
      console.log('Proceed with game:', response);
    });
  }

  // Step 3: Make a decision
  makeDecision(action: string): void {
    this.gameService.makeDecision(this.userId, action).subscribe((response) => {
      this.decisionMessage = response.message;
      console.log('Decision made:', response);
    });
  }
}
