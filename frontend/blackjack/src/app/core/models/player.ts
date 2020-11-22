import { User } from './user';
import { Card } from './card';
import { PlayerState } from './enums/player-state';

export interface Player {
    user: User;
    cards: Card[];
    points: number;
    state: PlayerState;
    bid: number;
}
