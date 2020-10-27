import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GameComponent } from './game.component';
import { GameListComponent } from './game-list/game-list.component';
import { AuthGuard } from '../core/guards/auth.guard';
import { BlackjackComponent } from './blackjack/blackjack.component';
import { GameGuard } from '../core/guards/game.guard';

const routes: Routes = [
  {
    path: '',
    component: GameComponent,
    canActivate: [AuthGuard],
    canActivateChild: [AuthGuard],
    children: [
      {
        path: '',
        component: GameListComponent
      },
      {
        path: 'game',
        component: BlackjackComponent,
        canActivate: [GameGuard]
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GameRoutingModule { }
