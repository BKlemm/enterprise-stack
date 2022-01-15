import {Component, Input, OnInit} from '@angular/core';
import {CarParkInterface, CarParkStatus} from "@frontend/administration/carpark/data-access";
import {BaseFormComponent} from "@frontend/shared/core";

@Component({
  selector: 'adm-carpark-form',
  templateUrl: './carpark-form.component.html',
  styleUrls: ['./carpark-form.component.scss']
})
export class CarparkFormComponent extends BaseFormComponent implements OnInit{

  @Input() carpark: CarParkInterface

  ngOnInit(): void {
    this.carpark.carParkStatus = CarParkStatus.INACTIVE
  }

  onChange() {
    this.carpark.carParkStatus = this.carpark.carParkStatus === CarParkStatus.ACTIVE
      ? CarParkStatus.INACTIVE
      : CarParkStatus.ACTIVE
  }

  get isActive() {
    return this.carpark.carParkStatus === CarParkStatus.ACTIVE
  }
}
