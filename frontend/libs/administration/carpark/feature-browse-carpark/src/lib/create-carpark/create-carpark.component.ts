import {Component} from '@angular/core';
import {TABMENU} from "../tab.menu";
import {AddCarpark, Carpark, CarparksFacade} from "@frontend/administration/carpark/data-access";
import {BaseComponent, OnCreate} from "@frontend/shared/core";
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

  constructor(private carparkFacade: CarparksFacade, private toast: ToastComponent) {
    super();
  }

  create() {
    this.subscription = this.carparkFacade.addCarpark(this.carpark).subscribe((carpark:Carpark) => {
      this.toast.show("Carpark angelegt");
      this.carpark = new AddCarpark()
    })
  }
}
