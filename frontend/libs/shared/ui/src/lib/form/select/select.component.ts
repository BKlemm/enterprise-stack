import { Component, OnInit } from "@angular/core";
import { FormGroup } from "@angular/forms";
import { FieldConfig } from "../field.interface";
@Component({
  selector: "ui-select",
  template: `
<div class="form-floating" [formGroup]="group">
<select id="{{field.name}}" [formControlName]="field.name">
<option *ngFor="let item of field.options" [value]="item">{{item}}</option>
  <label for="{{field.name}}">{{field.label}}</label>
</select>
</div>
`,
  styles: []
})
export class SelectComponent implements OnInit {
  field: FieldConfig;
  group: FormGroup;
  constructor() {}
  ngOnInit() {}
}
