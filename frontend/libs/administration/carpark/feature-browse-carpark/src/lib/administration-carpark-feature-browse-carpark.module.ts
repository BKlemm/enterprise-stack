import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowseCarparkComponent } from './browse-carpark/browse-carpark.component';
import {AdministrationCarparkUiModule} from "@frontend/administration/carpark/ui";
import {RouterModule, Routes} from "@angular/router";
import { CreateCarparkComponent } from './create-carpark/create-carpark.component';
import { EditCarparkComponent } from './edit-carpark/edit-carpark.component';
import {AdministrationSharedUiModule} from "@frontend/administration/shared/ui";
import {SharedUiModule} from "@frontend/shared/ui";
import {CarparkService} from "@frontend/administration/carpark/data-access";
import {ReactiveFormsModule} from "@angular/forms";

const routes: Routes = [
  {path: '', pathMatch: 'full', component: BrowseCarparkComponent},
  {path: 'create', pathMatch: 'full', component: CreateCarparkComponent},
  {
    path: ':id',
    children: [
      {path: '', component: EditCarparkComponent},
    ]
  },
]

@NgModule({
  imports: [
    CommonModule,
    AdministrationCarparkUiModule,
    AdministrationSharedUiModule,
    SharedUiModule,
    RouterModule.forChild(routes),
    ReactiveFormsModule
  ],
  declarations: [
    BrowseCarparkComponent,
    CreateCarparkComponent,
    EditCarparkComponent
  ],
  providers: [CarparkService]
})
export class ADMFeatureBrowseCarparkModule {}
