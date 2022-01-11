import {Component} from '@angular/core';
import {
  AddCarpark,
  Carpark,
  CarparksFacade,
  CarparkConfiguration
} from "@frontend/administration/carpark/data-access";
import {BaseComponent, OnCreate} from "@frontend/shared/core";
import {ToastComponent} from "../../../../../../shared/ui/src/lib/material/toast.component";

@Component({
  selector: 'frontend-create-carpark',
  templateUrl: './create-carpark.component.html',
  styleUrls: ['./create-carpark.component.scss'],
  providers: [ToastComponent, CarparkConfiguration]
})
export class CreateCarparkComponent extends BaseComponent implements OnCreate {

  carpark: AddCarpark = new AddCarpark()

  constructor(private carparkFacade: CarparksFacade, private toast: ToastComponent, configuration: CarparkConfiguration) {
    super(configuration);
  }

  create() {
    this.carparkFacade.addCarpark(this.carpark).subscribe((carpark:Carpark) => {
      this.toast.show("Carpark angelegt");
      this.carpark = new AddCarpark()
    })
  }

  get title() {
    return this.navigation.carpark.create.title
  }
}
