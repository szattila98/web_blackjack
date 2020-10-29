import { Component, Input, OnInit } from '@angular/core';
import { CardColor } from 'src/app/core/models/enums/card-color';
import { CardRank } from 'src/app/core/models/enums/card-rank';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent implements OnInit {

  @Input() number: number;
  @Input() rank: CardRank;
  @Input() color: CardColor;

  constructor() { }

  ngOnInit(): void {
  }

  get svgPath(): string {
    if (this.rank.toString() === CardRank[CardRank.ACE] || this.rank.toString() === CardRank[CardRank.JACK]
      || this.rank.toString() === CardRank[CardRank.KING] || this.rank.toString() === CardRank[CardRank.QUEEN]) {
        return `${this.rank.toString().toLowerCase()}_of_${this.color.toString().toLowerCase()}.svg`;
    }

    return `${this.number.toString().toLowerCase()}_of_${this.color.toString().toLowerCase()}.svg`;
  }

}
