import BaseStorage from './BaseStorage'

export class LocalStorage extends BaseStorage {

    getItems(key?: string) {
      if (localStorage.getItem((key ? key : this.key))) {
        try {
          return JSON.parse(localStorage.getItem((key ? key : this.key)) || Object.toString());
        } catch(e) {
          localStorage.removeItem((key ? key : this.key));
        }
      }
      return null;
    }

    sync(key?: string) {
      localStorage.setItem((key ? key : this.key), JSON.stringify(this._items));
    }
}
