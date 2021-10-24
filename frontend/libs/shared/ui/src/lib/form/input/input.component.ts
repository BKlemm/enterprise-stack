import { Component, OnInit } from "@angular/core";
import { FormGroup } from "@angular/forms";
import { FieldConfig } from "../field.interface";
@Component({
  selector: "ui-input",
  template: `
    <div class="form-floating mb-3" [formGroup]="group">
        <input id="{{field.name}}" class="form-control" [formControlName]="field.name" [placeholder]="field.label" [type]="field.inputType">
        <label for="{{field.name}}">{{field.label}}
          <ng-container *ngFor="let validation of field.validations;">
            <span *ngIf="group.get(field.name).hasError(validation.name)" class="text-danger">*</span>
          </ng-container></label>
    </div>
`,
  styles: []
})
export class InputComponent {
  field: FieldConfig;
  group: FormGroup;
}
