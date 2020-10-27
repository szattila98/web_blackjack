import { Component } from '@angular/core';
import { JoinService } from './join.service';
import { FormControl, Validators } from '@angular/forms';
import { IdentityService } from '../core/services/identity.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-join',
  templateUrl: './join.component.html',
  styleUrls: ['./join.component.scss']
})
export class JoinComponent {

  name: FormControl = new FormControl('', [Validators.required]);
  errorMessage: string;

  constructor(
    public joinService: JoinService,
    public identityService: IdentityService,
    public router: Router) { }

  joinUser(): void {
    this.errorMessage = null;
    this.joinService.joinUser(this.name.value).subscribe(
      res => {
        this.identityService.storeIdentity(res);
        this.router.navigateByUrl('/');
      },
      err => {
        this.errorMessage = err.error.msg;
      }
    );
  }

}
