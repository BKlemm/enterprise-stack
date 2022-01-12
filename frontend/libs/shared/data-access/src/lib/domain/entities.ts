export interface BaseAddress  {
  street: string
  number: string
  city: string
  country: string
  region: string
  zip: string
}

export class BaseAddressImpl implements BaseAddress {
  constructor(
    public street: string = 'Am Weg',
    public number: string = '4',
    public city: string = '',
    public country: string = 'Deutschland',
    public region: string = '',
    public zip: string = '73833'
  ) {}
}

export interface CarparkCollection {
  carparks: []
}
