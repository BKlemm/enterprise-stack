export interface Money {
  amount: number,
  currency: Currency
}
export interface Currency {
  symbol: string,
  code: string
}

export class MoneyImpl implements Money {
  constructor(
    public amount: number = 0,
    public currency: Currency = new CurrencyImpl()
  ) {}
}

export class CurrencyImpl implements Currency {
  constructor(
    public symbol: string = "€",
    public code: string = "EUR"
  ) {}
}

export interface Nav {
  [key: string]: NavRoutes
}

export interface NavData {
  title: string,
  link?: string
}

export interface NavRoutes {
  browse: NavData
  create?: NavData
  edit?: NavData
}

export class TableFilter {
  constructor(
    public activeSort: string = '',
    public filterPlaceholder: string = '',
    public sortDirection: string = 'asc',
    public filter: string = '',
    public pageIndex: number = 0,
    public pageSize: number = 10,
    public totalSize: number = 100
  ) {}
}
