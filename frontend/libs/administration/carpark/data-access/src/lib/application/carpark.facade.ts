import {BehaviorSubject, Observable, of} from "rxjs";
import {Injectable} from "@angular/core";
import {CarparkService} from "../infrastructure";
import {CollectionViewer, DataSource} from "@angular/cdk/collections";
import {catchError, finalize, map} from "rxjs/operators";
import {Carpark} from "../domain";
import {AddCarpark, CarParkResponse, ChangeCarpark} from "../api";
import {BaseFacade} from "@frontend/shared/core";

interface CarparkCollection {
  carparks: CarParkResponse[]
}

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

  load(activeSort: string = '', sortDirection: string = 'asc', filter: string = '', pageIndex: number = 0, pageSize: number = 10) {
    this.loadingSubject.next(true);
    this.carParkService.listByPageable(sortDirection, activeSort, filter, pageIndex, pageSize)
      .pipe(
        catchError(() => of([])),
        finalize(() => this.loadingSubject.next(false))
      )
      .subscribe((carparks: CarparkCollection) => {
        this.carParkSubject.next(carparks.carparks)
        this.counter = carparks.carparks.length
      })
  }

  loadById(id: string) {
    let response
    this.carParkService.get(id).subscribe((carpark:CarParkResponse) => {
      console.log(carpark)
      response = carpark
    })
    console.log(response)
    return response
  }

  loadActiveCarparks() {
    this.carParkService.list().subscribe((carparks:CarparkCollection) => {
      this.carParkSubject.next(carparks.carparks)
      this.counter = carparks.carparks.length
    })
  }

  addCarpark(carpark: AddCarpark): Observable<Carpark> {
    return this.carParkService.createSingle(carpark);
  }

  changeCarpark(carpark: ChangeCarpark): Observable<Carpark> {
    return this.carParkService.update(carpark.carParkId, carpark)
  }

  filteredCarpark(name: string) {
    this.carparks$
      .pipe(map(carparks => carparks.filter((carpark: CarParkResponse) => carpark.name.includes(name))))
      .subscribe()
  }

  getRoutedCarpark(id: string): CarParkResponse {
    let response: CarParkResponse
    this.carparks$
      .pipe(map(carparks => carparks.filter((carpark: CarParkResponse) => carpark.carParkId === id)))
      .subscribe(carpark => {
        response = carpark.pop()
      })
    return response
  }
}

