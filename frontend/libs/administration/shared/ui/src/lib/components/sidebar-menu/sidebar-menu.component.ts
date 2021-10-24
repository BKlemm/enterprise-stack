import { Component } from '@angular/core';
import {MENU_ROUTES} from "@frontend/administration/shared/util";

@Component({
  selector: 'adm-sidebar-menu',
  templateUrl: './sidebar-menu.component.html',
  styleUrls: ['./sidebar-menu.component.scss']
})
export class SidebarMenuComponent {

  menu = MENU_ROUTES

}
