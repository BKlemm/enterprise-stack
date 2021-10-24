import {Component, Input, OnInit, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'fe-register-form',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  @Input() isRegister: boolean = false
  @Output() isRegisterOpen = new EventEmitter<boolean>()

  constructor() { }

  ngOnInit(): void {
  }

}
