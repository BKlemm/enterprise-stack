import {AbstractConfiguration, TableFilter} from "@frontend/shared/core";
import {buildRoute, MENU_ROUTES, t} from "@frontend/administration/shared/util";
import {Injectable} from "@angular/core";

const ACTIVE_SORT = 'name'
const route = buildRoute(MENU_ROUTES.CARPARK)

@Injectable()
export class CarparkConfiguration extends AbstractConfiguration {

  createFilter() {
    this.filter = new TableFilter(ACTIVE_SORT, t`Suche Carpark`)
  }

  createNavigation() {
    this.navigation = {
      carpark: {
        browse: {title: t`Carparks`, link: route},
        create: {title: t`Neuen Carpark anlegen`, link: route + '/create'},
        edit: {title: t`Carpark bearbeiten`}
      },
    }
  }

  createTableDefinition() {
    this.columns = [
      {columnDef: ACTIVE_SORT, header: 'Airport', link: route, linkRef: 'carParkId'},
      {columnDef: 'iataCode', header: 'IATA Code'},
      {columnDef: 'description', header: 'Beschreibung'},
      {columnDef: 'tax', header: 'MwSt.'},
      {columnDef: 'carParkStatus', header: 'Status', primaryState: 'ACTIVE'}
    ]
  }
}
