//For ObjectMapper has to be a implement not a interface
import {AddressImpl} from "@frontend/website/shared/data-access";
import {CarParkStatus} from "../domain";

export class ChangeCarpark {
  protected constructor(
    public carParkId: string = '',
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

export class AddCarpark {
  constructor(
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
