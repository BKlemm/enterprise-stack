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
export class PrivilegesGuardService implements CanActivate {

  constructor(public auth: AuthService, public router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    // this will be passed from the route config on the data property
    const expectedPrivilege = route.data.expectedPrivilege;
    console.log(expectedPrivilege)
    const token = localStorage.getItem('id_token');

    if (!token) {
      this.router.navigate(['login']);
      return false;
    }

    // decode the token to get its payload
    const account: Account = decode(token)['user'];
    if (!this.auth.isLoggedIn() || !account.roles[0].privileges.some(e => e.name === expectedPrivilege)) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}
