import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RegisterOverlayComponent} from "./register-overlay/register-overlay.component";
import {RegisterComponent} from "./register/register.component";


@NgModule({
  declarations: [RegisterOverlayComponent, RegisterComponent],
  imports: [CommonModule],
  exports: [RegisterOverlayComponent]
})
export class WebsiteAuthenticateFeatureRegisterModule {}
