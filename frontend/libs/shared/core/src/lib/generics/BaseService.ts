import {HttpClient, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import {Observer, Responses, DataTransferObject, TableFilter} from '../types';
import {Injectable} from '@angular/core';
import {environment} from "../../../../../../apps/administration/src/environments/environment";

@Injectable()
export class BaseService {

  protected version!: string
  protected resource!: string
  protected gateway!: string
  protected subresource!: string
  private readonly defaultError

  constructor(protected http: HttpClient) {
    this.defaultError = 'Some Error occcured, Please contact Administrator for the Errors';
  }

  list<T>(filter: TableFilter, expand: Array<string> = [], options: unknown = {}, subroute: string = '') {
    Object.assign(options, {
      params: new HttpParams()
        .set('filter', JSON.stringify(filter.httpParams()))
        .set('expand', expand.join(','))
    })
    return this.handleHateoas(this.http.get<Responses & T>(this.endpoint(subroute), options))
  }

  get<T>(id: string, options: unknown = {}, expand: Array<string> = []): Observable<T> {
    Object.assign(options, {params: {expand: expand.join(',')}})
    return this.http.get<Responses & T>(this.endpoint(id),options)
  }

  create<T>(data: DataTransferObject, options: unknown = {}): Observable<T> {
    Object.assign(options, {observe: 'response'})
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
    const idPath = id !== '' ? '/' + id : '';
    const _resource = this.resource.replace("{id}", id)
    return environment.endpoint + this.gateway + '/' + this.version + '/' + _resource + ( !this.resource.includes("{id}") ? idPath : '')
  }

  private handleHateoas<T>(response: any): Observable<T> {
    return new Observable((observer: Observer) => {
      response.subscribe((response: any) => {
        if (response.errors && response.errors.length > 0) {
          observer.error(response.errors);
        } else {
          observer.next(response._embedded);
        }
        observer.complete();
      }, (error: any) => {
        observer.error([{ title: error.name, detail: this.defaultError, error }]);
      });
    });
  }
}
