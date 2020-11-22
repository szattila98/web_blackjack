import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { IdentityService } from 'src/app/core/services/identity.service';
import { GameService } from '../../game.service';

@Component({
  selector: 'app-bid-modal',
  templateUrl: './bid-modal.component.html',
  styleUrls: ['./bid-modal.component.scss']
})
export class BidModalComponent implements OnInit {

  @Input() gameId: string;
  userId: string;
  bid = 0;

  loading: boolean;
  errorMessage: string;

  constructor(
    public activeModal: NgbActiveModal,
    public gameService: GameService,
    public identityService: IdentityService) { }

  ngOnInit(): void {
    this.userId = this.identityService.getIdentity().id;
  }

  placeBid(): void {
    this.loading = true;
    this.gameService.raiseBid(this.gameId, this.userId, this.bid).subscribe(
      res => {
        this.loading = false;
        this.activeModal.close();
      },
      err => {
        this.errorMessage = err.error.msg || 'Unknown error';
        this.loading = false;
      });
  }

}
