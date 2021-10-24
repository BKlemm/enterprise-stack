import {Injectable} from "@angular/core";

@Injectable()
export class ObjectMapper<T,E> {

  source: T
  target: E

  constructor(source: T, target: E) {
    this.source = source
    this.target = target
  }

  static transpose(source, target) {
    const clazz = new target()
    const properties = Object.getOwnPropertyNames(clazz)
    properties.forEach(prop => {
      clazz[prop] = source[prop]
    })
    return clazz
  }

  transform(): E {
    const properties = this.getPropertyNames()
    properties.forEach(prop => {
      this.target[prop] = this.source[prop]
    })
    return this.target
  }

  getPropertyNames(): Array<string> {
    return Object.getOwnPropertyNames(this.target)
  }
}
