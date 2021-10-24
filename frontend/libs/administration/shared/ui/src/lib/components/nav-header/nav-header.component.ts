import {Component, Input, Output, EventEmitter} from '@angular/core';


@Component({
  selector: 'adm-nav-header',
  templateUrl: './nav-header.component.html',
  styleUrls: ['./nav-header.component.scss']
})
export class NavHeaderComponent {

  @Input() isSidebarOpen = false
  @Output() sidebarClicked = new EventEmitter<boolean>()

  toogleSidebar() {
    this.isSidebarOpen = !this.isSidebarOpen
  }

}
