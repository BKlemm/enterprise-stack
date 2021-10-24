import BaseStorage from './BaseStorage'

export class SessionStorage extends BaseStorage {

  getItems(key?: string) {
    if (sessionStorage.getItem((key ? key : this.key))) {
      try {
        return JSON.parse(sessionStorage.getItem((key ? key : this.key)) || Object.toString());
      } catch(e) {
        sessionStorage.removeItem((key ? key : this.key));
      }
    }
    return null;
  }

  sync(key?: string) {
    sessionStorage.setItem((key ? key : this.key), JSON.stringify(this._items));
  }
}
