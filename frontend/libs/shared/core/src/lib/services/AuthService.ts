import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http'
import * as moment from "moment";
import {DataTransferObject} from "../types";
import {Route} from "../decorators";
import {BaseService} from "../generics";

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

  login(email: string, password: string) {
    return this.create({email, password})
      .subscribe(res => AuthService.setSession)
  }

  private static setSession(authResult) {
    const expiresAt = moment().add(authResult.expiresIn);

    localStorage.setItem('id_token', authResult.idToken);
    localStorage.setItem("expires_at", JSON.stringify(expiresAt.valueOf()) );
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
