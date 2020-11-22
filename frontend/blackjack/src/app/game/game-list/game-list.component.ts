import { Component, OnInit, Input } from '@angular/core';
import { IdentityService } from 'src/app/core/services/identity.service';
import { User } from 'src/app/core/models/user';
import { GameService } from '../game.service';
import { Game } from 'src/app/core/models/game';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html',
  styleUrls: ['./game-list.component.scss']
})
export class GameListComponent implements OnInit {

  user: User;
  games: Game[] = [];

  currencyToRefill = 0;
  isCurrencyRefillInProgress = false;
  refillErrorMessage: string;

  isRefreshInProgress = false;
  isCreateInProgress = false;
  errorMessage: string;

  constructor(
    public identityService: IdentityService,
    public gameService: GameService,
    public userService: UserService,
    public router: Router) { }

  ngOnInit(): void {
    const client = this.identityService.getIdentity();
    this.user = client;
    this.userService.getUser(client.id).subscribe(
      res => {
        this.user = res;
      },
      err => {
        console.error(err);
      }
    );

    this.updateGamesList();
  }

  refillCurrency(): void {
    this.isCurrencyRefillInProgress = true;
    this.refillErrorMessage = '';

    this.userService.refillCurrency(this.user.id, this.currencyToRefill).subscribe(
      res => {
        this.user.currency += this.currencyToRefill;
        this.currencyToRefill = 0;
        this.isCurrencyRefillInProgress = false;
      },
      err => {
        this.refillErrorMessage = err.error.msg;
        this.isCurrencyRefillInProgress = false;
      }
    );
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
