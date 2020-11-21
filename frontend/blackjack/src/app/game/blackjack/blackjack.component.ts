import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Game } from 'src/app/core/models/game';
import { GameService } from '../game.service';
import { Subscription, interval } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { User } from 'src/app/core/models/user';
import { IdentityService } from 'src/app/core/services/identity.service';
import { PlayerState } from 'src/app/core/models/enums/player-state';
import { GameState } from 'src/app/core/models/enums/game-state';

interface StatusMessage {
  error: boolean;
  message: string;
}

@Component({
  selector: 'app-blackjack',
  templateUrl: './blackjack.component.html',
  styleUrls: ['./blackjack.component.scss']
})
export class BlackjackComponent implements OnInit, OnDestroy {

  gameId: string;
  game: Game;
  user: User;
  status: StatusMessage;

  refreshInterval = 1000;
  subscription: Subscription;
  playerStates = PlayerState;
  gameStates = GameState;

  constructor(
    public route: ActivatedRoute,
    public router: Router,
    public gameService: GameService,
    public identityService: IdentityService) { }

  ngOnInit(): void {
    this.gameId = this.route.snapshot.queryParams.id;
    this.user = this.identityService.getIdentity();
    this.loadGame();

    this.subscription = interval(this.refreshInterval)
      .pipe(switchMap(() => this.gameService.getGame(this.gameId)))
      .subscribe(
        gameUpdate => {
          if (this.isGameFull && gameUpdate.currentPlayerIndex < gameUpdate.players.length
            && gameUpdate.players[gameUpdate.currentPlayerIndex].user.id === this.user.id) {
            this.status = {
              error: false,
              message: 'It\'s your turn.'
            };
          }

          if (this.isGameFull && gameUpdate.currentPlayerIndex < gameUpdate.players.length
            && gameUpdate.players[gameUpdate.currentPlayerIndex].user.id !== this.user.id) {
            this.status = {
              error: false,
              message: `It\'s ${gameUpdate.players[gameUpdate.currentPlayerIndex].user.name}'s turn.`
            };
          }

          if (this.isGameFull && gameUpdate.currentPlayerIndex === gameUpdate.players.length) {
            this.status = {
              error: false,
              message: 'It\'s the dealer\'s turn.'
            };
          }

          if (gameUpdate.state.toString() === this.gameStates[this.gameStates.CLOSED]) {
            this.status = {
              error: false,
              message: 'Game\'s ended.'
            };
          }

          this.game = gameUpdate;
        },
        err => {
          this.status = {
            error: true,
            message: `Failed to update game state: ${err.error.msg}`
          };
        });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  get isGameFull(): boolean {
    return this.game.players.length === 2;
  }

  loadGame(): void {
    this.gameService.getGame(this.gameId).subscribe(
      res => {
        this.game = res;
      },
      err => {
        this.status = {
          error: true,
          message: err.error.msg
        };
      }
    );
  }

  standPlayer(): void {
    this.gameService.standPlayer(this.game.id, this.user.id).subscribe(
      res => {
        this.status = {
          error: false,
          message: 'You are stopped successfully.'
        };
      },
      err => {
        this.status = {
          error: true,
          message: err
        };
      }
    );
  }

  hitPlayer(): void {
    this.gameService.hitPlayer(this.game.id, this.user.id).subscribe(
      res => {
        this.status = {
          error: false,
          message: 'Hit was successful.'
        };
      },
      err => {
        this.status = {
          error: true,
          message: err
        };
      }
    );
  }

  navigateToGameList(): void {
    this.router.navigateByUrl('/');
  }

}
