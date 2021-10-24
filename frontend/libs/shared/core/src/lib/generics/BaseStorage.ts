
export default abstract class BaseStorage {
  protected _items: any[] = []
  protected key: string = '';

  constructor(key: string) {
    this.key = key

    let i;
    if ((i = this.getItems()) != null){
      this._items = i
    }
  }

  add(item: any, key?: string) {
    this._items = item
    this.sync(key)
  }

  get items() {
    return this._items.slice(0)
  }

  abstract sync(key?: string): void
  abstract getItems(): any
}
