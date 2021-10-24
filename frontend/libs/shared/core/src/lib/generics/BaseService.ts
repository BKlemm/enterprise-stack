import {HttpClient, HttpParams} from '@angular/common/http';
import { environment } from "../../../../../../apps/website/src/environments/environment";
import { Observable } from 'rxjs';
import {Observer, Responses, ServiceInterface, DataTransferObject} from '../types';
import { Injectable } from '@angular/core';

@Injectable()
export class BaseService implements ServiceInterface {

    protected version!: string;
    protected resource!: string;
    protected subresource!: string;
    private defaultError;

    constructor(protected http: HttpClient) {
        this.defaultError = 'Some Error occcured, Please contact Administrator for the Errors';
    }

    listBy<T>(sortOrder: string = 'asc', filter: string = '', pageNumber: number = 0, pageSize: number = 10) {
      return this.http.get<Responses & T>(this.endpoint(),{
        params: new HttpParams()
          .set('filter', filter)
          .set('sort', sortOrder)
          .set('page', pageNumber.toString())
          .set('size', pageSize.toString())
      })
    }

    list<T>(expand: string = "", options: unknown = {}): Observable<T> {
        return this.http.get<Responses & T>(this.endpoint(expand), options);
    }

    get<T>(id: string, options: unknown = {}): Observable<T> {
        return this.http.get<Responses & T>(this.endpoint(id), options);
    }

    create<T>(data: DataTransferObject, options: unknown = {}): Observable<T> {
        Object.assign(options, {observe: 'response'})
        return this.http.post<Responses & T>(this.endpoint(), data, options)
    }

    createSingle<T>(data: DataTransferObject, options: unknown = {}): Observable<T> {
        return this.http.post<Responses & T>(this.endpoint(), data, options)
    }

    update<T>(id: string, data: DataTransferObject, options: unknown = {}): Observable<T> {
        Object.assign(options, {observe: 'response'})
        return this.http.put<Responses & T>(this.endpoint(id), data, options);
    }

    delete(id: string, options: unknown = {}): Observable<Responses> {
        return this.http.delete<Responses>(this.endpoint(id), options);
    }

    private endpoint(id: string = '') {
        const idPath = id === '' ? '' : '/' + id;
        const _resource = this.resource.replace("{id}", id)
        return environment.endpoint + this.version + '/' + _resource + ( !this.resource.includes("{id}") ? idPath : '')
    }


    private handleResponse<T>(response: any): Observable<T> {
        return new Observable((observer: Observer) => {
            response.subscribe((response: any) => {
                if (response.errors && response.errors.length > 0) {
                    observer.error(response.errors);
                } else {
                    observer.next(response.success);
                }
                observer.complete();
            }, (error: any) => {
              observer.error([{ title: error.name, detail: this.defaultError, error }]);
            });
        });
    }
}
