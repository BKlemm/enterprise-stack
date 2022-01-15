import {TableFilter} from "@frontend/shared/core";
import {BehaviorSubject} from "rxjs";

export abstract class BaseFacade {

  protected loadingSubject = new BehaviorSubject<boolean>(false)
  protected counterSubject = new BehaviorSubject<number>(0)

  public readonly loading$ = this.loadingSubject.asObservable()
  public readonly counter$ = this.counterSubject.asObservable()
  public counter = 0;

  abstract load(filter: TableFilter, expand: Array<string>, subroute: string)
}
