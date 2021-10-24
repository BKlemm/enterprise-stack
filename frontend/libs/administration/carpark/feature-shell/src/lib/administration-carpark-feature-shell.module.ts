import { NgModule } from '@angular/core';
import { ShellComponent } from './shell/shell.component';
import {RouterModule, Routes} from "@angular/router";
import {ADMCarparkDataAccessModule} from "@frontend/administration/carpark/data-access";

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'browse-carpark'},
  {
    path: 'browse-carpark',
    loadChildren: () => import('@frontend/administration/carpark/feature-browse-carpark').then(m => m.ADMFeatureBrowseCarparkModule)
  }
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    ADMCarparkDataAccessModule,
  ],
  declarations: [
    ShellComponent
  ],
  exports: [
    RouterModule
  ],
})
export class ADMCarparkFeatureShellModule {}
