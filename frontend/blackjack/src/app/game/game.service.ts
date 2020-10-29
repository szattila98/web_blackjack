import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Game } from '../core/models/game';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor(public http: HttpClient) { }

  getAvailableGames(userId: string): Observable<Game[]> {
    return this.http.get<Game[]>('/api/game', {
      params: {
        userId
      }
    });
  }

  joinGame(userId: string, gameId: string): Observable<Game> {
    return this.http.post<Game>(`/api/game/${gameId}/user/${userId}/join`, null);
  }

  createGame(userId: string): Observable<Game> {
    return this.http.post<Game>('/api/game', { inputValue: userId });
  }

  getGame(gameId: string): Observable<Game> {
    return this.http.get<Game>(`/api/game/${gameId}`);
  }

  standPlayer(gameId: string, userId: string): Observable<Game> {
    return this.http.post<Game>(`/api/game/${gameId}/user/${userId}/stand`, null);
  }

  hitPlayer(gameId: string, userId: string): Observable<Game> {
    return this.http.post<Game>(`/api/game/${gameId}/user/${userId}/hit`, null);
  }
}
