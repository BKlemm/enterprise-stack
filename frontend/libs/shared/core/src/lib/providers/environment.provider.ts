import {Environment} from "@frontend/shared/core";
import {InjectionToken} from "@angular/core";

export const ENV = new InjectionToken<Environment>('env')

export function getEnvs(environment: Environment) {
  return environment
}
