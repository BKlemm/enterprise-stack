import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LoginOverlayComponent} from "./login-overlay/login-overlay.component";
import {ReactiveFormsModule} from "@angular/forms";
import {LoginComponent} from "./login/login.component";
import {AuthGuardService} from "@frontend/shared/core";

@NgModule({
  declarations: [LoginOverlayComponent, LoginComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
  ],
  exports: [LoginOverlayComponent],
  providers: [AuthGuardService]
})
export class WebsiteAuthenticateFeatureLoginModule {}
