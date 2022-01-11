import { Injectable } from '@angular/core';
import {
  Router,
  CanActivate,
  ActivatedRouteSnapshot
} from '@angular/router';
import { AuthService } from '../base';
import decode from 'jwt-decode';
import {Account} from "./account";

@Injectable({ providedIn: 'root' })
export class RoleGuardService implements CanActivate {

  constructor(public auth: AuthService, public router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    // this will be passed from the route config
    // on the data property
    const expectedRole = route.data.expectedRole;
    const token = localStorage.getItem('id_token');
    // decode the token to get its payload

    if (!token) {
      this.router.navigate(['login']);
      return false;
    }

    const account: Account = decode(token)['user'];
    if (!this.auth.isLoggedIn() || !account.roles.some(e => e.name === expectedRole)) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}
