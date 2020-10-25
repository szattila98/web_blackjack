import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { IdentityService } from '../services/identity.service';

@Injectable({
  providedIn: 'root'
})
export class JoinGuard implements CanActivate {

  constructor(
    public identityService: IdentityService,
    public router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this.identityService.getIdentity()) {
      this.router.navigateByUrl('/list');
      return false;
    }

    return true;
  }

}
