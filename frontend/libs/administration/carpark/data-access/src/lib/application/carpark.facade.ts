import {BehaviorSubject, Observable, of} from "rxjs";
import {Injectable} from "@angular/core";
import {CarparkService} from "../infrastructure";
import {CollectionViewer, DataSource} from "@angular/cdk/collections";
import {catchError, finalize, map} from "rxjs/operators";
import {Carpark} from "../domain";
import {AddCarpark, CarParkResponse, ChangeCarpark} from "../api";
import {BaseFacade, TableFilter} from "@frontend/shared/core";

@Injectable()
export class CarparksFacade extends BaseFacade implements DataSource<CarParkResponse>{

  private carParkSubject = new BehaviorSubject<CarParkResponse[]>([])

  public readonly carparks$ = this.carParkSubject.asObservable()

  constructor(private carParkService: CarparkService) {super()}

  connect(collectionViewer: CollectionViewer): Observable<CarParkResponse[]> {
    return this.carParkSubject.asObservable()
  }

  disconnect(collectionViewer: CollectionViewer): void {
    this.carParkSubject.complete()
    this.loadingSubject.complete()
    this.counterSubject.complete()
  }

  load(filter?: TableFilter, expand: Array<string> = [], subroute: string = '') {
    this.loadingSubject.next(true);
    this.carParkService.list(filter, expand, {}, subroute)
      .pipe(
        catchError(() => of([])),
        finalize(() => this.loadingSubject.next(false))
      )
      .subscribe((carparks: CarParkResponse[]) => {
        this.carParkSubject.next(carparks)
        this.counter = carparks.length
      })
  }

  addCarpark(carpark: AddCarpark): Observable<Carpark> {
    return this.carParkService.create(carpark);
  }

  changeCarpark(carpark: ChangeCarpark): Observable<Carpark> {
    return this.carParkService.update(carpark.carParkId, carpark)
  }

  filteredCarpark(name: string) {
    this.carparks$
      .pipe(map(carparks => carparks.filter((carpark: CarParkResponse) => carpark.name.includes(name))))
      .subscribe()
  }
}

