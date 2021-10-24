import { Observable } from "rxjs";

export interface ServiceInterface {

    list(): Observable<unknown> ;
    get(id: string): Observable<unknown>;
    update(id: string, data: string): void;
    create(data: string): void;
    delete(id: string): void;
}

export class Observer {
    next!: Function;
    error!: Function;
    complete!: Function;
}

export class SingleResponse {
    public status!: number;
    public IsSuccess!: boolean;
    public details!: string;
    public body!: unknown;
}

export class Responses {
    public errors: SingleResponse[] = [];
    public success: SingleResponse[] = [];
}

export interface Link {
  rel: string,
  href: string
}

export interface DataResponse<T> {
  content: T
  totalElements: number
  links: Link[]
}
// eslint-disable-next-line @typescript-eslint/no-empty-interface
export interface EntityInterface {}
// eslint-disable-next-line @typescript-eslint/no-empty-interface
export interface DataTransferObject {

}
// eslint-disable-next-line @typescript-eslint/no-empty-interface
export interface DataResponseObject {

}
