import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {WebsiteUiModule} from "@frontend/website/shared/ui";
import {HttpClientModule, HttpClientXsrfModule} from "@angular/common/http";
import {SharedUiModule} from "@frontend/shared/ui";
import {CarparksModule} from "./carparks/carparks.module";
import { DashboardModule } from './dashboard/dashboard.module';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    HttpClientXsrfModule.withOptions({cookieName: 'XSRF-TOKEN', headerName: 'X-XSRF-TOKEN'}),
    BrowserAnimationsModule,
    WebsiteUiModule,
    SharedUiModule,
    CarparksModule,
    DashboardModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
