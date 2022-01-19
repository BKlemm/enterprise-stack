import {BehaviorSubject, Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {Carpark} from '../domain'
import {CarparkService} from "../infrastructure";
import {CollectionViewer, DataSource} from "@angular/cdk/collections";

@Injectable()
export class BrowseCarparksFacade implements DataSource<Carpark>{

  private carParkSubject = new BehaviorSubject<Carpark[]>([])

  public carparks$ = this.carParkSubject.asObservable()

  constructor(private carParkService: CarparkService) {}

  connect(collectionViewer: CollectionViewer): Observable<Carpark[]> {
    return this.carParkSubject.asObservable()
  }

  disconnect(collectionViewer: CollectionViewer): void {
    this.carParkSubject.complete()
  }

  load() {
    this.carParkService.list()
      .subscribe((carparks: Carpark[]) => {
        this.carParkSubject.next(carparks)
      })
    return this
  }
}
