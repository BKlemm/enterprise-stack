import {Component, Input} from '@angular/core';
import {CarParkInterface} from "@frontend/administration/carpark/data-access";
import {BaseFormComponent} from "@frontend/shared/core";

@Component({
  selector: 'adm-carpark-form',
  templateUrl: './carpark-form.component.html',
  styleUrls: ['./carpark-form.component.scss']
})
export class CarparkFormComponent extends BaseFormComponent {

  @Input() carpark: CarParkInterface

  constructor() { super() }

  onChange() {
    this.carpark.carParkStatus = this.carpark.carParkStatus === 'ACTIVE' ? 'INACTIVE' : 'ACTIVE'
  }
}
