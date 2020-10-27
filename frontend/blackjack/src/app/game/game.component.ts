import { Component, OnInit } from '@angular/core';
import { IdentityService } from '../core/services/identity.service';
import { Router } from '@angular/router';
import { User } from '../core/models/user';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {

  user: User;

  constructor(
    public identityService: IdentityService,
    public router: Router
  ) { }

  ngOnInit(): void {
    this.user = this.identityService.getIdentity();
  }

  logout(): void {
    this.identityService.removeIdentity();
    this.router.navigateByUrl('/join');
  }

}
