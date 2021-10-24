import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MENU_ROUTES} from "@frontend/administration/shared/util";


const routes: Routes = [
  { path: MENU_ROUTES.DASHBOARD, loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule)},
  { path: MENU_ROUTES.CARPARK, loadChildren: () => import('./carparks/carparks.module').then(m => m.CarparksModule)},
  { path: '**', redirectTo: MENU_ROUTES.CARPARK}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
