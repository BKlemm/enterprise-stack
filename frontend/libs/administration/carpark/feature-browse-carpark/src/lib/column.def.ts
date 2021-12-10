import {Column} from "@frontend/shared/core";

export const COLUMNS: string[] = ['name', 'iataCode','description', 'tax', 'carParkStatus'];
export const TABLE_COLUMN_DEFINITION: Column[] = [
  {columnDef: COLUMNS[0], header: 'Airport', link: '/carparks', linkRef: 'carParkId'},
  {columnDef: COLUMNS[1], header: 'IATA Code'},
  {columnDef: COLUMNS[2], header: 'Beschreibung'},
  {columnDef: COLUMNS[3], header: 'MwSt.'},
  {columnDef: COLUMNS[4], header: 'Status', primaryState: 'ACTIVE'}
]
