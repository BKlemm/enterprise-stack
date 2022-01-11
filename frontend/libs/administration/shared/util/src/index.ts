export * from './lib/administration-shared-util.module';

export const MENU_ROUTES = {
  DASHBOARD: 'dashboard',
  LOGIN: 'login',
  CARPARK: 'carpark'
};

export const buildRoute = route => '/' + route
export const t = $localize => $localize

