import { Player } from './player';
import { Dealer } from './dealer';
import { GameState } from './enums/game-state';

export interface Game {
    id: string;
    players: Player[];
    dealer: Dealer;
    currentPlayerIndex: number;
    state: GameState;
}