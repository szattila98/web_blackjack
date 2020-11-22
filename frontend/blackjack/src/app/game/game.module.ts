import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GameRoutingModule } from './game-routing.module';
import { GameComponent } from './game.component';
import { GameListComponent } from './game-list/game-list.component';
import { BlackjackComponent } from './blackjack/blackjack.component';
import { CardComponent } from './blackjack/card/card.component';
import { BidModalComponent } from './blackjack/bid-modal/bid-modal.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    GameComponent,
    GameListComponent,
    BlackjackComponent,
    CardComponent,
    BidModalComponent
  ],
  imports: [
    CommonModule,
    GameRoutingModule,
    FormsModule
  ]
})
export class GameModule { }
