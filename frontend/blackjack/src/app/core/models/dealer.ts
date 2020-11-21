import { Card } from './card';
import { PlayerState } from './enums/player-state';

export interface Dealer {
    cards: Card[];
    points: number;
    state: PlayerState;
}
