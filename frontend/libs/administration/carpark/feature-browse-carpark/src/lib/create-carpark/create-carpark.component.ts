import {Component} from '@angular/core';
import {TABMENU} from "../tab.menu";
import {AddCarpark, CarparkService} from "@frontend/administration/carpark/data-access";
import {BaseComponent, HTTP, OnCreate, SingleResponse} from "@frontend/shared/core";
import {ToastComponent} from "../../../../../../shared/ui/src/lib/material/toast.component";

@Component({
  selector: 'frontend-create-carpark',
  templateUrl: './create-carpark.component.html',
  styleUrls: ['./create-carpark.component.scss'],
  providers: [ToastComponent]
})
export class CreateCarparkComponent extends BaseComponent implements OnCreate {

  tabs = TABMENU
  title = 'Neuen Parkplatz anlegen'

  carpark: AddCarpark = new AddCarpark()

  constructor(private carparkService: CarparkService, private toast: ToastComponent) {
    super();
  }

  create() {
    this.subscription = this.carparkService.create(this.carpark).subscribe((response:SingleResponse) => {
      if (response.status === HTTP.CREATED) {
        this.toast.show("Carpark angelegt");
        this.carpark = new AddCarpark()
      }
    })
  }
}
