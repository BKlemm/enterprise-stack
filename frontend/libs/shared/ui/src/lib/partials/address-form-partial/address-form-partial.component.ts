import {Component, Input} from '@angular/core';
import {AddressImpl} from "@frontend/website/shared/data-access";


@Component({
  selector: 'ui-address-form-partial',
  templateUrl: './address-form-partial.component.html',
  styleUrls: ['./address-form-partial.component.scss']
})
export class AddressFormPartialComponent {

  @Input() address: AddressImpl

}
