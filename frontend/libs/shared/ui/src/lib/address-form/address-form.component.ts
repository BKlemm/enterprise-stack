import {Component, Input} from '@angular/core';
import {COUNTRIES, REGIONS} from "../../../../core/src/lib/data/country.data";
import {Address} from "@frontend/website/shared/data-access";

@Component({
  selector: 'ui-address-form',
  templateUrl: './address-form.component.html',
  styleUrls: ['./address-form.component.scss']
})
export class AddressFormComponent {

  countries = COUNTRIES
  regions = REGIONS
  @Input() address: Address

}
