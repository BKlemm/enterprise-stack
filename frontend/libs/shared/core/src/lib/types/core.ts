import { Observable } from "rxjs";
import {NavData, NavRoutes, TableFilter} from "./global";

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

export interface Pageable {
  totalElements: number
  totalPages: number
}

export interface DataResponse<T> {
  _embedded: T
  pages?: Pageable
  links?: Link[]
}
// eslint-disable-next-line @typescript-eslint/no-empty-interface
export interface EntityInterface {}
// eslint-disable-next-line @typescript-eslint/no-empty-interface
export interface DataTransferObject {

}
// eslint-disable-next-line @typescript-eslint/no-empty-interface
export interface DataResponseObject {

}

export interface Column {
  columnDef: string,
  dep?: string,
  header: string,
  link?: string,
  linkRef?: string,
  primaryState?: string
}

export class NavRoutesImpl {
  action: string
  title: string
  generateLink: boolean
  link?: string

  constructor(action: string, title: string, generateLink: boolean = true) {
    this.action = action
    this.title = title
    this.generateLink = generateLink
  }
}

export class Navigation {
  mainRoute: string
  selector: string
  navigation: Map<string, NavRoutesImpl> = new Map<string, NavRoutesImpl>()

  constructor(route: string) {
    this.mainRoute = route
    this.selector = route
  }

  addSubroute(route: string) {
    this.selector = route
    return this
  }

  add(action: NavRoutesImpl) {
    if (action.generateLink) {
      action.link = this.mainRoute + '/' + this.selector
    }
    this.navigation.set(this.selector, action)
    return this
  }

  build() {
    return this.navigation.entries()
  }
}

export abstract class Configuration {
  navigation: Record<string, NavRoutes>
  tabs: NavData[] = []
  filter: TableFilter
  displayedColumns: string[] = []
  columns: Column[]

  constructor() {
    this.createNavigation()
    this.createFilter()
    this.createTableDefinition()

    this.createTabs()
    this.createDisplayedColumns()
  }

  private createTabs() {
    for(const [key, value] of Object.entries(this.navigation)){
      this.tabs.push(value['browse'])
    }
  }

  private createDisplayedColumns() {
    for(const value of this.columns) {
      this.displayedColumns.push(value.columnDef)
    }
  }

  abstract createFilter()
  abstract createNavigation()
  abstract createTableDefinition()
}
