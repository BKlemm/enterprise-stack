import {TableFilter} from "@frontend/shared/core";
import {BehaviorSubject} from "rxjs";

export abstract class BaseFacade {

  protected loadingSubject = new BehaviorSubject<boolean>(false)
  protected counterSubject = new BehaviorSubject<number>(0)

  public readonly loading$ = this.loadingSubject.asObservable()
  public readonly counter$ = this.counterSubject.asObservable()
  public counter = 0;

  loadByFilter(filter: TableFilter, subroute: string = '') {
    this.load(filter.activeSort, filter.sortDirection, filter.filter, filter.pageIndex, filter.pageSize, subroute);
  }

  abstract load(activeSort: string, sortDirection: string, filter: string, pageIndex: number, pageLength: number, subroute: string)
}
