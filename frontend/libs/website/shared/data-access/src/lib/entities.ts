import {BaseAddress, BaseAddressImpl} from "@frontend/shared/data-access";

export interface Price {
  amount: number,
  amountNet: number,
  tax: number
}

export interface Address extends BaseAddress {
  latitude: string,
  longitude: string
}

export class AddressImpl extends BaseAddressImpl implements Address {
  constructor(
    public latitude: string = '',
    public longitude: string = ''
  ) {super()}
}
