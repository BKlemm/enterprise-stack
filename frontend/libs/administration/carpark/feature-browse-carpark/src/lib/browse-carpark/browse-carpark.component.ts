import { Component, OnInit } from '@angular/core';
import {TABMENU} from "../tab.menu";
import {CarparkService, CarparksFacade} from "@frontend/administration/carpark/data-access";
import {COLUMNS, TABLE_COLUMN_DEFINITION} from "../column.def";

@Component({
  selector: 'feature-browse-carpark',
  templateUrl: './browse-carpark.component.html',
  styleUrls: ['./browse-carpark.component.scss'],
})
export class BrowseCarparkComponent implements OnInit {

  tabs = TABMENU
  title = `Carparks Übersicht`

  displayedColumns = COLUMNS
  columns = TABLE_COLUMN_DEFINITION;

  dataSource: CarparksFacade

  activeSort: string = ""
  sortDirection: string = 'asc'
  totalSize: number = 100

  constructor(private carparkService: CarparkService) {}

  ngOnInit(): void {
    this.dataSource = new CarparksFacade(this.carparkService)
    if (this.dataSource.counter === 0) {
      this.dataSource.load(this.activeSort, this.sortDirection)
    }
  }

}
