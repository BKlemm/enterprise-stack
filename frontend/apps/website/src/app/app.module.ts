import {NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import {WebsiteUiModule} from "@frontend/website/shared/ui";
import {APP_BASE_HREF} from "@angular/common";
import {SharedUiModule} from "@frontend/shared/ui";
import { HomeModule } from './home/home.module';
import {EventEmitterService} from "@frontend/website/shared/services";


@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    WebsiteUiModule,
    SharedUiModule,
    HomeModule,
  ],
  providers: [
    EventEmitterService,
    {provide: APP_BASE_HREF, useValue: '/business-anwendungen'}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

export class WebsiteSharedModule {}
