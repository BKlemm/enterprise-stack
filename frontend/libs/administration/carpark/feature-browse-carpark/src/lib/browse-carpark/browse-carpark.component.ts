import { Component, OnInit } from '@angular/core';
import {TABMENU} from "../tab.menu";
import {CarparksFacade} from "@frontend/administration/carpark/data-access";
import {BaseTableComponent} from "@frontend/shared/core";

@Component({
  selector: 'feature-browse-carpark',
  templateUrl: './browse-carpark.component.html',
  styleUrls: ['./browse-carpark.component.scss'],
})
export class BrowseCarparkComponent extends BaseTableComponent implements OnInit {

  tabs = TABMENU
  title = `Carparks Übersicht`

  constructor(private carparkFacade: CarparksFacade) {super()}

  ngOnInit(): void {
    if (this.carparkFacade.counter === 0) {
      this.carparkFacade.load()
    }
  }

}
