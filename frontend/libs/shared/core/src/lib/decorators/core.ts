type Constructor = { new (...args: any[]): {} }

export function V1<T extends Constructor>(constructor: T) {
  return class extends constructor {
    version = "v1";
  };
}

export function Resource(resource: string) {
  return function <T extends Constructor>(constructor: T){
    return class extends constructor {
      resource = resource
    }
  }
}

export function Version(version: string) {
  return function <T extends Constructor>(constructor: T){
    return class extends constructor {
      version = version
    }
  }
}

interface Parameters {
  gateway?: string
  version?: string,
  resource?: string
}

export function Route(params: Parameters|any ) {
  return function <T extends Constructor>(constructor: T){
    return class extends constructor {
      version = params.version ? params.version : 'v1'
      resource = params.resource ? params.resource : params
      gateway = params.gateway ? params.gateway : 'parking'
    }
  }
}

export function route(resource: string) {
  return function(target: any, propertyKey: string, descriptor: PropertyDescriptor) {
    let value : string;
    const getter = function() {
      return value;
    };
    const setter = function(newVal: string) {
      value = resource;
    };
    Object.defineProperty(target, 'resource', {
      get: getter,
      set: setter
    })
  }
}

export function Min(limit: number) {
  return function(target: unknown, propertyKey: string) {
    let value : string;
    const getter = function() {
      return value;
    };
    const setter = function(newVal: string) {
      if(newVal.length < limit) {
        Object.defineProperty(target, 'errors', {
          value: `Your field should be bigger than ${limit}`
        });
      }
      else {
        value = newVal;
      }
    };
    Object.defineProperty(target, propertyKey, {
      get: getter,
      set: setter
    });
  }
}

export function Max(limit: number) {
  return function(target: unknown, propertyKey: string) {
    let value : string;
    const getter = function() {
      return value;
    };
    const setter = function(newVal: string) {
      if(newVal.length > limit) {
        Object.defineProperty(target, 'errors', {
          value: `Your field should be smaller than ${limit}`
        });
      }
      else {
        value = newVal;
      }
    };
    Object.defineProperty(target, propertyKey, {
      get: getter,
      set: setter
    });
  }
}

