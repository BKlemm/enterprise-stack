import {Component, EventEmitter, Input, Output} from "@angular/core";

@Component({
  template: ''
})
export abstract class BaseFormComponent {

  @Input() public buttonColor = 'btn-primary'
  @Input() public buttonName = 'create'
  @Input() public buttonLabel: string

  @Output() submit = new EventEmitter()

  public onSubmit(): void {
    this.submit.emit()
  }
}
