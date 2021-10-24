import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HomeComponent} from "@website/home/home.component";
import {WebsiteUiModule} from "@frontend/website/shared/ui";
import {HomeRoutingModule} from "@website/home/home-routing.module";
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";



@NgModule({
  declarations: [HomeComponent],
  imports: [
    CommonModule,
    HomeRoutingModule,
    WebsiteUiModule,
    NgSelectModule,
    FormsModule
  ],
  exports: [HomeComponent]
})
export class HomeModule { }
