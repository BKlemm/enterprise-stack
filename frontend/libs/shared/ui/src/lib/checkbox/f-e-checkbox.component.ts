import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'fe-checkbox',
  templateUrl: './f-e-checkbox.component.html',
  styleUrls: ['./f-e-checkbox.component.scss']
})
export class FECheckboxComponent {

  @Output() checked = new EventEmitter<boolean>()
  @Input() label = ''
  @Input() id = ''

  check(value: boolean) {
    this.checked.emit(value)
  }

}
