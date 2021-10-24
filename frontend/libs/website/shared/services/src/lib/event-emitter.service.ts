
import {Injectable, EventEmitter} from '@angular/core';

@Injectable({providedIn: 'root'})
export class EventEmitterService {

    tariff = new EventEmitter();
    reservation = new EventEmitter()

    sendTariff(data: any) {
        this.tariff.emit(data)
    }

    sendReservation(data: any) {
      this.reservation.emit(data)
    }
}
