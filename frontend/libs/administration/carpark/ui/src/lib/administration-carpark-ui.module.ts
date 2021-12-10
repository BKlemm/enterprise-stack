import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatInputModule} from "@angular/material/input";
import {RouterModule} from "@angular/router";
import {CarparkFormComponent} from "./carpark-form/carpark-form.component";
import {SharedUiModule} from "@frontend/shared/ui";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CarparkService, CarparksFacade} from "@frontend/administration/carpark/data-access";

@NgModule({
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatProgressSpinnerModule,
    MatInputModule,
    RouterModule,
    SharedUiModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  declarations: [
    CarparkFormComponent,
  ],
  exports: [CarparkFormComponent],
  providers: [CarparksFacade, CarparkService]
})
export class AdministrationCarparkUiModule {}
