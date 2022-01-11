import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BreadcrumbComponent } from './breadcrumb/breadcrumb.component';
import {RouterModule} from "@angular/router";
import { SidebarRightComponent } from './sidebar-right/sidebar-right.component';
import {NavtabsComponent} from "./navtabs/navtabs.component";
import { FECheckboxComponent } from './checkbox/f-e-checkbox.component';
import {OverlayComponent} from "./overlay/overlay.component";
import { CardComponent } from './card/card.component';
import { AddressFormComponent } from './address-form/address-form.component';
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputComponent} from "./form/input/input.component";
import {ButtonComponent} from "./form/button/button.component";
import {SelectComponent} from "./form/select/select.component";
import {DynamicFieldDirective} from "./form/dynamic-field/dynamic-field.directive";
import {DynamicFormComponent} from "./form/dynamic-form/dynamic-form.component";
import {SwitchComponent} from "./form/switch/switch.component";
import { DashCardComponent } from './dash-card/dash-card.component';
import {TableComponent} from "./table/table.component";
import {MatTableModule} from "@angular/material/table";
import {MatSortModule} from "@angular/material/sort";
import {MatIconModule} from "@angular/material/icon";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatPaginatorModule} from "@angular/material/paginator";
import {SharedCoreModule} from "@frontend/shared/core";
import { AddressFormPartialComponent } from './partials/address-form-partial/address-form-partial.component';
import {ToastComponent} from "./material/toast.component";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import { ExpansionPanelComponent } from './expansion-panel/expansion-panel.component';
import {MatExpansionModule} from "@angular/material/expansion";

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    NgSelectModule,
    ReactiveFormsModule,
    MatTableModule,
    MatSortModule,
    MatIconModule,
    MatFormFieldModule,
    MatSnackBarModule,
    MatPaginatorModule,
    SharedCoreModule,
    MatExpansionModule
  ],
  declarations: [
    BreadcrumbComponent,
    SidebarRightComponent,
    NavtabsComponent,
    FECheckboxComponent,
    OverlayComponent,
    CardComponent,
    AddressFormComponent,
    InputComponent,
    ButtonComponent,
    SelectComponent,
    SwitchComponent,
    DynamicFieldDirective,
    DynamicFormComponent,
    DashCardComponent,
    TableComponent,
    AddressFormPartialComponent,
    ToastComponent,
    ExpansionPanelComponent,
  ],
  exports: [
    BreadcrumbComponent,
    SidebarRightComponent,
    NavtabsComponent,
    FECheckboxComponent,
    OverlayComponent,
    CardComponent,
    AddressFormComponent,
    SwitchComponent,
    InputComponent,
    ButtonComponent,
    SelectComponent,
    DynamicFormComponent,
    DashCardComponent,
    TableComponent,
    AddressFormPartialComponent,
    ToastComponent,
    ExpansionPanelComponent,
  ]
})
export class SharedUiModule {}
