import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowseCarparkComponent } from './browse-carpark/browse-carpark.component';
import {AdministrationCarparkUiModule} from "@frontend/administration/carpark/ui";
import {RouterModule, Routes} from "@angular/router";
import { CreateCarparkComponent } from './create-carpark/create-carpark.component';
import { EditCarparkComponent } from './edit-carpark/edit-carpark.component';
import {AdministrationSharedUiModule} from "@frontend/administration/shared/ui";
import {SharedUiModule} from "@frontend/shared/ui";
import {CarparkConfiguration, CarparkService, CarparksFacade} from "@frontend/administration/carpark/data-access";
import {ReactiveFormsModule} from "@angular/forms";
import {MatExpansionModule} from "@angular/material/expansion";

const routes: Routes = [
  {path: '', pathMatch: 'full', component: BrowseCarparkComponent},
  {path: 'create', pathMatch: 'full', component: CreateCarparkComponent},
  {path: ':id', component: EditCarparkComponent},
]

@NgModule({
    imports: [
        CommonModule,
        AdministrationCarparkUiModule,
        AdministrationSharedUiModule,
        SharedUiModule,
        RouterModule.forChild(routes),
        ReactiveFormsModule,
        MatExpansionModule
    ],
  declarations: [
    BrowseCarparkComponent,
    CreateCarparkComponent,
    EditCarparkComponent
  ],
  providers: [CarparksFacade, CarparkService, CarparkConfiguration]
})
export class ADMFeatureBrowseCarparkModule {}
