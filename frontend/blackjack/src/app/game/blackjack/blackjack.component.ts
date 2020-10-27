import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-blackjack',
  templateUrl: './blackjack.component.html',
  styleUrls: ['./blackjack.component.scss']
})
export class BlackjackComponent implements OnInit {

  gameId: string;

  constructor(public route: ActivatedRoute) { }

  ngOnInit(): void {
    this.gameId = this.route.snapshot.queryParams.id;
  }

}
