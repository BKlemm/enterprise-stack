import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {DataPropertyGetterPipe} from "./directives";

@NgModule({
  imports: [CommonModule],
  declarations: [DataPropertyGetterPipe],
  exports: [DataPropertyGetterPipe]
})
export class SharedCoreModule {}
