import { Component, OnInit } from "@angular/core";
import { FormGroup } from "@angular/forms";
import {FieldConfig} from "../field.interface";

@Component({
  selector: "ui-switch",
  template: `
    <div class="form-check form-switch" [formGroup]="group">
      <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault">
      <label class="form-check-label" for="flexSwitchCheckDefault">{{field.label}}}</label>
    </div>
`,
  styles: []
})
export class SwitchComponent implements OnInit {
  field: FieldConfig;
  group: FormGroup;
  constructor() {}
  ngOnInit() {}
}
