import {Component, Input, OnInit, Output, EventEmitter} from '@angular/core';
import {animate, style, transition, trigger} from "@angular/animations";

@Component({
  selector: 'fe-login-overlay',
  templateUrl: './login-overlay.component.html',
  styleUrls: ['./login-overlay.component.scss'],
  animations: [
    trigger('myInsertRemoveTrigger', [
      transition(':enter', [
        style({ opacity: 0 }),
        animate('300ms ease-in', style({ opacity: 1 })),
      ]),
      transition(':leave', [
        animate('300ms ease-out', style({ opacity: 0 }))
      ])
    ]),
  ]
})
export class LoginOverlayComponent {

  @Input() isLogin: boolean
  @Output()  isLoginOpen = new EventEmitter<boolean>()

  constructor() { }

  closeLogin() {
    this.isLoginOpen.emit(false)
  }

}
