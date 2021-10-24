import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'ui-dynamic-form',
  templateUrl: './dynamic-form.component.html',
  styleUrls: ['./dynamic-form.component.scss']
})
export class DynamicFormComponent implements OnInit {

  formGroup: FormGroup;
  @Input() formTemplate: any;
  constructor() {}

  ngOnInit() {

    let group={}
    this.formTemplate.forEach(input=>{
      group[input.label] = new FormControl('');
    })
    this.formGroup = new FormGroup(group);
  }

  onSubmit(){
    console.log(this.formGroup.value);
  }

}
