import {BehaviorSubject, Observable, of} from "rxjs";
import {Injectable} from "@angular/core";
import {CarparkService} from "../infrastructure";
import {CollectionViewer, DataSource} from "@angular/cdk/collections";
import {catchError, finalize, map} from "rxjs/operators";
import {Carpark, CarparkImpl} from "../domain";

@Injectable()
export class CarparksFacade implements DataSource<Carpark>{

  private carParkSubject = new BehaviorSubject<Carpark[]>([])
  private loadingSubject = new BehaviorSubject<boolean>(false)
  private counterSubject = new BehaviorSubject<number>(0)

  public readonly carparks$ = this.carParkSubject.asObservable()
  public readonly loading$ = this.loadingSubject.asObservable()
  public readonly counter$ = this.counterSubject.asObservable()
  public counter = 0;

  constructor(private carParkService: CarparkService) {}

  connect(collectionViewer: CollectionViewer): Observable<Carpark[]> {
    return this.carParkSubject.asObservable()
  }

  disconnect(collectionViewer: CollectionViewer): void {
    //this.carParkSubject.complete()
    this.loadingSubject.complete()
    this.counterSubject.complete()
  }

  load(sortDirection: string = 'asc', filter: string = '', pageIndex: number = 0, pageSize: number = 10) {
    this.loadingSubject.next(true);
    this.carParkService.listBy(sortDirection, filter, pageIndex, pageSize)
      .pipe(
        catchError(() => of([])),
        finalize(() => this.loadingSubject.next(false))
      )
      .subscribe((carparks: Carpark[]) => {
        this.carParkSubject.next(carparks)
        this.counter = carparks.length
      })
  }

  loadById(id: string) {
    this.carParkService.get(id).subscribe((carpark:Carpark) => {
      this.carParkSubject.next([carpark])
    })
  }

  loadActiveCarparks() {
    return this.carParkService.list()
  }
}

