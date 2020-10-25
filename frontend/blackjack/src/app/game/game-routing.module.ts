import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GameComponent } from './game.component';
import { GameListComponent } from './game-list/game-list.component';
import { AuthGuard } from '../core/guards/auth.guard';
import { BlackjackComponent } from './blackjack/blackjack.component';

const routes: Routes = [
  {
    path: '',
    component: GameComponent,
    canActivate: [AuthGuard],
    canActivateChild: [AuthGuard],
    children: [
      {
        path: '',
        component: BlackjackComponent
      },
      {
        path: 'list',
        component: GameListComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GameRoutingModule { }
