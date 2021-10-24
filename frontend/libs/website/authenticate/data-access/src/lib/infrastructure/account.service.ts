import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http'
import {BaseService, Route} from "@frontend/shared/core";

@Route('accounts')
@Injectable({
    providedIn: 'root'
})
export class AccountService extends BaseService {

    constructor(protected http: HttpClient) {
        super(http)
    }
}
