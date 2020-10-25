import { Component } from '@angular/core';
import { IdentityService } from '../core/services/identity.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent {

  constructor(
    public identityService: IdentityService,
    public router: Router
  ) { }

  logout(): void {
    this.identityService.removeIdentity();
    this.router.navigateByUrl('/join');
  }

}
