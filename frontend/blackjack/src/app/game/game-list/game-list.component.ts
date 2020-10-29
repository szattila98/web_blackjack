import { Component, OnInit, Input } from '@angular/core';
import { IdentityService } from 'src/app/core/services/identity.service';
import { User } from 'src/app/core/models/user';
import { GameService } from '../game.service';
import { Game } from 'src/app/core/models/game';
import { Router } from '@angular/router';

@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html',
  styleUrls: ['./game-list.component.scss']
})
export class GameListComponent implements OnInit {

  user: User;
  games: Game[] = [];
  isRefreshInProgress = false;
  isCreateInProgress = false;
  errorMessage: string;

  constructor(
    public identityService: IdentityService,
    public gameService: GameService,
    public router: Router) { }

  ngOnInit(): void {
    this.user = this.identityService.getIdentity();
    this.updateGamesList();
  }

  updateGamesList(): void {
    this.gameService.getAvailableGames(this.user.id).subscribe(
      res => {
        this.games = res;
        this.isRefreshInProgress = false;
      },
      err => {
        this.errorMessage = err.error.msg;
        this.isRefreshInProgress = false;
      }
    );
  }

  refreshList(): void {
    this.errorMessage = '';
    this.isRefreshInProgress = true;
    this.updateGamesList();
  }

  joinGame(gameId: string): void {
    this.errorMessage = '';
    this.gameService.joinGame(this.user.id, gameId).subscribe(
      res => {
        this.router.navigate(['/game'], {
          queryParams: {
            id: res.id
          }
        });
      },
      err => {
        this.errorMessage = err.error.msg;
      }
    );
  }

  addNewGame(): void {
    this.errorMessage = '';
    this.isCreateInProgress = true;
    this.gameService.createGame(this.user.id).subscribe(
      res => {
        this.isCreateInProgress = false;
        this.router.navigate(['/game'], {
          queryParams: {
            id: res.id
          }
        });
      },
      err => {
        this.errorMessage = err.error.msg;
        this.isCreateInProgress = false;
      }
    );
  }
}
