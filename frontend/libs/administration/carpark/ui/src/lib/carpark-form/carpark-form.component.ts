import {Component, Input} from '@angular/core';
import {Carpark} from "@frontend/administration/carpark/data-access";
import {BaseFormComponent} from "@frontend/shared/core";

@Component({
  selector: 'adm-carpark-form',
  templateUrl: './carpark-form.component.html',
  styleUrls: ['./carpark-form.component.scss']
})
export class CarparkFormComponent extends BaseFormComponent {

  @Input() carpark: Carpark

  constructor() { super() }

  onChange() {
    this.carpark.state = this.carpark.state === 'ACTIVE' ? 'INACTIVE' : 'ACTIVE'
  }
}
