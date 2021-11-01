import { Component, OnInit } from '@angular/core';
import {TABMENU} from "../tab.menu";
import {CarparksFacade} from "@frontend/administration/carpark/data-access";
import {BaseTableComponent} from "@frontend/shared/core";

interface Column {
  columnDef: string,
  dep?: string
  header: string,
  link?: string,
  linkRef?: string
}

@Component({
  selector: 'feature-browse-carpark',
  templateUrl: './browse-carpark.component.html',
  styleUrls: ['./browse-carpark.component.scss'],
})
export class BrowseCarparkComponent extends BaseTableComponent implements OnInit {

  tabs = TABMENU
  title = 'Carparks Übersicht'

  displayedColumns: string[] = ['name', 'iataCode', 'description', 'tax', 'state'];
  columns: Column[] = [
    {columnDef: this.displayedColumns[0], header: 'Name', link: '/carpark', linkRef: 'carParkId'},
    {columnDef: this.displayedColumns[1], header: 'IATA Code'},
    {columnDef: this.displayedColumns[2], header: 'Beschreibung'},
    {columnDef: this.displayedColumns[3], header: 'MwSt.'},
    {columnDef: this.displayedColumns[4], header: 'Status'}
  ]

  constructor(private carparkFacade: CarparksFacade) {super()}

  ngOnInit(): void {
    this.dataSource = this.carparkFacade;
    if (this.carparkFacade.counter === 0) {
      this.dataSource.load()
    }
  }

}
