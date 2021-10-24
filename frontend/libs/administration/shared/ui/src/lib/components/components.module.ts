import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {NavHeaderComponent} from "./nav-header/nav-header.component";
import {SidebarMenuComponent} from "./sidebar-menu/sidebar-menu.component";
import {SidebarLeftComponent} from "./sidebar-left/sidebar-left.component";
import {FooterComponent} from "./footer/footer.component";
import {NavHeaderLoginComponent} from "./nav-header-login/nav-header-login.component";
import {RouterModule} from "@angular/router";
import {SharedUiModule} from "@frontend/shared/ui";
import { TableComponent } from './table/table.component';
import {MatTableModule} from "@angular/material/table";
import {MatSortModule} from "@angular/material/sort";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatInputModule} from "@angular/material/input";
import {MatPaginatorModule} from "@angular/material/paginator";



@NgModule({
  declarations: [
    NavHeaderComponent,
    NavHeaderLoginComponent,
    SidebarMenuComponent,
    SidebarLeftComponent,
    FooterComponent,
    TableComponent,
  ],
  imports: [
    CommonModule,
    RouterModule,
    SharedUiModule,
    MatTableModule,
    MatSortModule,
    MatProgressSpinnerModule,
    MatInputModule,
    MatPaginatorModule
  ],
  exports: [
    NavHeaderComponent,
    NavHeaderLoginComponent,
    SidebarMenuComponent,
    SidebarLeftComponent,
    FooterComponent,
    TableComponent
  ]
})
export class ComponentsModule { }
