import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ComponentsModule} from "./components/components.module";
import {LayoutComponent} from "./layout/default/layout.component";
import {RouterModule} from "@angular/router";
import {WebsiteAuthenticateFeatureRegisterModule} from "@frontend/website/authenticate/feature-register";
import {WebsiteAuthenticateFeatureLoginModule} from "@frontend/website/authenticate/feature-login";
import {SharedUiModule} from "@frontend/shared/ui";
import {BsDropdownModule} from "ngx-bootstrap/dropdown";

@NgModule({
    imports: [
        CommonModule,
        ComponentsModule,
        RouterModule,
        WebsiteAuthenticateFeatureLoginModule,
        WebsiteAuthenticateFeatureRegisterModule,
        SharedUiModule,
        BsDropdownModule.forRoot(),
    ],
  declarations: [
    LayoutComponent,
  ],
  exports: [
    LayoutComponent,
  ],
})
export class WebsiteUiModule {}
