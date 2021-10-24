import {Component, Input} from '@angular/core';

@Component({
  selector: 'adm-sidebar-left',
  templateUrl: './sidebar-left.component.html',
  styleUrls: ['./sidebar-left.component.scss']
})
export class SidebarLeftComponent {

  @Input() isSidebarOpen = false

  toogleSidebar() {
    this.isSidebarOpen = !this.isSidebarOpen
  }

}
