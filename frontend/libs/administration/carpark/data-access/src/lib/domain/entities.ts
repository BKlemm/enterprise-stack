import {DataTransferObject} from "@frontend/shared/core";
import {Address, AddressImpl} from "@frontend/website/shared/data-access";

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

abstract class BaseCarpark implements BaseCarparkInterface {
  protected constructor(
    public iataCode: string = '',
    public description: string  = '',
    public name:  string  = '',
    public state: string  = '',
    public supportEmail: string = '',
    public supportPhone: string = '',
    public address: AddressImpl = new AddressImpl(),
    public tax: number = 19
  ) {}
}

export interface Carpark extends BaseCarparkInterface {
  carParkId: string
}

export class CarparkDTO extends BaseCarpark implements DataTransferObject {
  constructor() {super()}
}

export class CarparkImpl extends BaseCarpark implements Carpark {

  constructor(
    public carParkId: string = '',
  ) {super()}
}

export interface CarparkListResponse {
  content: Carpark[],
  totalElements: number
}

