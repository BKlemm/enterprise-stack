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
