import {Component, Input} from '@angular/core';
import {Address} from "@frontend/website/shared/data-access";

@Component({
  selector: 'ui-address-form',
  templateUrl: './address-form.component.html',
  styleUrls: ['./address-form.component.scss']
})
export class AddressFormComponent {

  @Input() address: Address

}
