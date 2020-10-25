import { CardRank } from './enums/card-rank';
import { CardColor } from './enums/card-color';

export interface Card {
    number: number;
    rank: CardRank;
    color: CardColor;
}
