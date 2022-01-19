import {AddressImpl} from "@frontend/website/shared/data-access";
import {CarParkStatus} from "../domain";
import {BaseCarpark} from "../core";

class GenerateLots {
  constructor(
    public autogenerate: boolean = true,
    public amount: number = 0,
    public lotPrefix: string = 'A',
    public areaPrefix: string = 'B',
    public name: string = '',
    public startNumber: number = 1,
    public finalNumber: number = 100
  ) {}
}

export class AddCarpark extends BaseCarpark {
  public lots: GenerateLots = new GenerateLots()
}

export class ChangeCarpark extends BaseCarpark {
  protected constructor(
    public carParkId: string,
    public iataCode: string = '',
    public description: string  = '',
    public name:  string  = '',
    public state: string  = '',
    public supportEmail: string = '',
    public supportPhone: string = '',
    public address: AddressImpl = new AddressImpl(),
    public tax: number = 19,
    public carParkStatus: string = CarParkStatus.INACTIVE
  ) {super(iataCode, description, name, state, supportEmail, supportPhone, address, tax)}
}
