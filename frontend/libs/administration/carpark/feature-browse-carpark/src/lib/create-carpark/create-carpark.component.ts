import {Component} from '@angular/core';
import {TABMENU} from "../tab.menu";
import {AddCarpark, CarparkService} from "@frontend/administration/carpark/data-access";
import {BaseComponent, HTTP, OnCreate, SingleResponse} from "@frontend/shared/core";

@Component({
  selector: 'frontend-create-carpark',
  templateUrl: './create-carpark.component.html',
  styleUrls: ['./create-carpark.component.scss']
})
export class CreateCarparkComponent extends BaseComponent implements OnCreate {

  tabs = TABMENU
  title = 'Neuen Parkplatz anlegen'

  carpark: AddCarpark = new AddCarpark()

  constructor(private carparkService: CarparkService) {
    super();
  }

  create() {
    this.subscription = this.carparkService.create(this.carpark).subscribe((response:SingleResponse) => {
      if (response.status === HTTP.CREATED) {
        alert("Erfolgreich angelegt")
        this.carpark = new AddCarpark()
      }
    })
  }
}
