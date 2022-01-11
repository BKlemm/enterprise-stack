import {AddressImpl} from "@frontend/website/shared/data-access";

export class BaseCarpark {
  constructor(
    public iataCode: string = '',
    public description: string  = '',
    public name:  string  = '',
    public state: string  = '',
    public supportEmail: string = '',
    public supportPhone: string = '',
    public address: AddressImpl = new AddressImpl(),
    public tax: number = 19,
  ) {}
}
