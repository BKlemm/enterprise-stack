import {Address} from "@frontend/website/shared/data-access";

interface BaseCarparkInterface {
  iataCode: string
  description: string
  name:  string
  state: string
  supportEmail: string
  supportPhone: string
  address: Address
  tax: number
}

export interface Carpark extends BaseCarparkInterface {
  carParkId: string
}

export enum CarParkStatus {
  INACTIVE = "INACTIVE",
  ACTIVE = "ACTIVE",
}

export interface CarparkListResponse {
  content: Carpark[],
  totalElements: number
}
