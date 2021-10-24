import {Price} from "@frontend/website/shared/data-access";

export interface Carpark {
  carParkId: string
  iataCode: string
  description: string
  name:  string
  state: string
}

export interface Cart {
    carParkId: string
    price: Price
    arrival: string
    departure: string
    tariffDescription: string
    tariffName: string
    tariffId: string
    trackbackCode?: string
}
