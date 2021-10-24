import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import {BaseService, Route} from "@frontend/shared/core";

@Route('carparks')
@Injectable()
export class CarparkService extends BaseService {

    constructor(protected http: HttpClient) {
        super(http)
    }
}
