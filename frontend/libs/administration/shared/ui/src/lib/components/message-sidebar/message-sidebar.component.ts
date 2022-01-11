import {Component, Input} from '@angular/core';

@Component({
  selector: 'adm-messagebar-left',
  templateUrl: './message-sidebar.component.html'
})
export class MessageSidebarComponent {

  @Input() isMessagebarOpen = false

  toogleMessagebar() {
    this.isMessagebarOpen = !this.isMessagebarOpen
  }

}
