// game.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'  // This ensures the service is available globally
})
export class GameService {
  private baseUrl = 'http://localhost:8090/GamePi/api/game'; // Replace with your Spring backend URL

  constructor(private http: HttpClient) {}

  // Step 1: Start the game
  startGame(userId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/start/${userId}`, {});
  }

  // Step 2: Proceed with the game
  proceedWithGame(userId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/proceed/${userId}`, {});
  }

  // Step 3: Make a decision
  makeDecision(userId: number, action: string): Observable<any> {
    return this.http.post(`${this.baseUrl}/decision`, null, {
      params: {
        userId: userId.toString(),
        action: action, // BUY, SELL, HOLD
      },
    });
  }

  

}
