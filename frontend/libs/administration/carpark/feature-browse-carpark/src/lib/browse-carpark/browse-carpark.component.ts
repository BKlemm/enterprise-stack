import { Component, OnInit } from '@angular/core';
import {TABMENU} from "../tab.menu";
import {CarparkService, CarparksFacade} from "@frontend/administration/carpark/data-access";

@Component({
  selector: 'feature-browse-carpark',
  templateUrl: './browse-carpark.component.html',
  styleUrls: ['./browse-carpark.component.scss'],
})
export class BrowseCarparkComponent implements OnInit {

  tabs = TABMENU
  title = 'Carparks Übersicht'

  constructor() {

  }

  ngOnInit(): void {
  }

}
