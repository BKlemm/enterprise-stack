import {BaseService, Route} from "@frontend/shared/core";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";

@Route({gateway:"api", resource:"carparks"})
@Injectable({providedIn: 'root'})
export class CarparkService extends BaseService {

  public constructor(protected http: HttpClient) {
    super(http);
  }
}
