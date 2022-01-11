import {Component, Input} from '@angular/core';
import {NavData, NavRoutes} from "@frontend/shared/core";

@Component({
  selector: 'ui-navtabs',
  templateUrl: './navtabs.component.html',
  styleUrls: ['./navtabs.component.scss']
})
export class NavtabsComponent {

  @Input() title: string = 'Instanz bearbeiten'
  @Input() tabs: NavData[]
  @Input() navData: NavRoutes

}
