import { Player } from './player';
import { GameState } from './enums/game-state';

export interface Game {
    id: string;
    players: Player[];
    currentPlayerIndex: number;
    state: GameState;
}