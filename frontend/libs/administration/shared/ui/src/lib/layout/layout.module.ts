import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {DefaultComponent} from "./default/default.component";
import {EmptyComponent} from "./empty/empty.component";
import {ComponentsModule} from "../components/components.module";




@NgModule({
  declarations: [
    DefaultComponent,
    EmptyComponent
  ],
  imports: [
    CommonModule,
    ComponentsModule
  ],
  exports: [
    DefaultComponent,
    EmptyComponent
  ]
})
export class LayoutModule { }
