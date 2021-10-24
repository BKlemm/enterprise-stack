import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashboardRoutingModule } from './dashboard-routing.module';
import {AdministrationSharedUiModule} from "@frontend/administration/shared/ui";
import {DashboardComponent} from "./dashboard.component";
import {SharedUiModule} from "@frontend/shared/ui";
import {NgxEchartsModule} from "ngx-echarts";


@NgModule({
  declarations: [
    DashboardComponent
  ],
  imports: [
    CommonModule,
    DashboardRoutingModule,
    NgxEchartsModule.forRoot({echarts: () => import('echarts')}),
    SharedUiModule,
    AdministrationSharedUiModule
  ]
})
export class DashboardModule { }
