import { NgModule } from '@angular/core';
import { CarparksComponent } from './carparks.component';
import {ADMFeatureBrowseCarparkModule} from "@frontend/administration/carpark/feature-browse-carpark";
import {RouterModule} from "@angular/router";



@NgModule({
  declarations: [
    CarparksComponent
  ],
  imports: [
    ADMFeatureBrowseCarparkModule,
    RouterModule
  ]
})
export class CarparksModule { }
