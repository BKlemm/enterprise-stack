import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http'
import * as moment from "moment";
import {DataTransferObject} from "../types";
import {Route} from "../decorators";
import {BaseService} from "../generics";
import decode from "jwt-decode";
import {map} from "rxjs/operators";

interface Credentials extends DataTransferObject{
  email: string,
  password: string,
}

@Route("login")
@Injectable({
  providedIn: 'root'
})
export class AuthService extends BaseService {

  constructor(protected http: HttpClient) {
    super(http)
  }

  private static setSession(authResult) {
    const token = decode(authResult.body.token)
    const expiresAt = moment().add(token['exp']);

    localStorage.setItem('id_token', authResult.body.token);
    localStorage.setItem("expires_at", JSON.stringify(expiresAt.valueOf()) );
  }

  login(userName: string, password: string) {
    return this.create({userName, password})
      .pipe(map(res => AuthService.setSession(res)))
  }

  logout() {
    localStorage.removeItem("id_token");
    localStorage.removeItem("expires_at");
  }

  public isLoggedIn() {
    return moment().isBefore(this.getExpiration());
  }

  isLoggedOut() {
    return !this.isLoggedIn();
  }

  getExpiration() {
    const expiration = localStorage.getItem("expires_at");
    const expiresAt = JSON.parse(expiration);
    return moment(expiresAt);
  }
}
