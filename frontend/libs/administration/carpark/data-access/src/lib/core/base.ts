import {AddressImpl} from "@frontend/website/shared/data-access";
import {CarParkStatus} from "@frontend/administration/carpark/data-access";

export class BaseCarpark {

  constructor(
    public iataCode: string = '',
    public description: string  = 'Premium Parken',
    public name:  string  = '',
    public state: string  = CarParkStatus.INACTIVE,
    public supportEmail: string = 'san@exmaple.com',
    public supportPhone: string = '01-292-29292929',
    public address: AddressImpl = new AddressImpl(),
    public tax: number = 19,
  ) {}
}
