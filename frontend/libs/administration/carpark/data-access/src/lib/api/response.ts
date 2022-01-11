import {Address} from "@frontend/website/shared/data-access";
import {CarParkId} from "../domain/valueobjects";

export interface CarParkInterface {
  iataCode: string
  description: string
  name:  string
  carParkStatus: string
  supportEmail: string
  supportPhone: string
  address: Address
  tax: number,
  lots?: Lot[]
}

export interface CarParkResponse extends CarParkInterface {
  carParkId: string,
  created: string,
}

export interface Lot {
  lotNumber: string,
  carParkId: CarParkId,
  reservation?: Reservation
}

interface Reservation {
  reservationId: string,
  reservationNumber: string
}
