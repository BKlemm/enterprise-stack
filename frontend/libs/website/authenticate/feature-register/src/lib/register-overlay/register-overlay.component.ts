import {Component, Input, OnInit, Output, EventEmitter} from '@angular/core';
import {animate, style, transition, trigger} from "@angular/animations";

@Component({
  selector: 'fe-register-overlay',
  templateUrl: './register-overlay.component.html',
  styleUrls: ['./register-overlay.component.scss'],
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
export class RegisterOverlayComponent implements OnInit {

  @Input() isRegister: boolean
  @Output() isRegisterOpen = new EventEmitter<boolean>()

  constructor() { }

  ngOnInit(): void {
  }

  closeRegister() {
    this.isRegisterOpen.emit(false)
  }

}
