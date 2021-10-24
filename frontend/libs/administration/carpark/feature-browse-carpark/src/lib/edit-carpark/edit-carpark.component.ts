import {Component, OnDestroy, OnInit} from '@angular/core';
import {TABMENU} from "../tab.menu";
import {Carpark, CarparkService} from "@frontend/administration/carpark/data-access";
import {ActivatedRoute} from "@angular/router";
import {BaseComponent} from "@frontend/shared/core";

@Component({
  selector: 'frontend-edit-carpark',
  templateUrl: './edit-carpark.component.html',
  styleUrls: ['./edit-carpark.component.scss']
})
export class EditCarparkComponent extends BaseComponent implements OnInit, OnDestroy {

  tabs = TABMENU
  title = 'Parkplatz bearbeiten'

  carpark: Carpark
  routedId: string

  constructor(private carparkService: CarparkService, private route: ActivatedRoute) {
    super()
  }

  ngOnInit(): void {
    this.routedId = this.route.snapshot.paramMap.get('id')
    this.subscription = this.carparkService.get(this.routedId).subscribe((carpark:Carpark) => {
      this.carpark = carpark
    })
  }

  updateCarpark() {
    this.subscription = this.carparkService.update(this.routedId, this.carpark).subscribe(() => {

    })
  }

  ngOnDestroy() {
    this.subscription.unsubscribe()
  }

}
