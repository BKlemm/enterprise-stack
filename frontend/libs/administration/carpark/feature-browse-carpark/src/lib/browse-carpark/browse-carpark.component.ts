import { Component, OnInit } from '@angular/core';
import {CarparkService, CarparksFacade, CarparkConfiguration} from "@frontend/administration/carpark/data-access";
import {BaseComponent} from "@frontend/shared/core";

@Component({
  selector: 'feature-browse-carpark',
  templateUrl: './browse-carpark.component.html',
  styleUrls: ['./browse-carpark.component.scss'],
})
export class BrowseCarparkComponent extends BaseComponent implements OnInit {

  dataSource: CarparksFacade

  constructor(private carparkService: CarparkService, configuration: CarparkConfiguration) {super(configuration)}

  ngOnInit(): void {
    this.dataSource = new CarparksFacade(this.carparkService)
    if (this.dataSource.counter === 0) {
      this.dataSource.load(this.filter.activeSort, this.filter.sortDirection)
    }
  }

}
