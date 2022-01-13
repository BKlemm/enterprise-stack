import {AfterViewInit, Component, OnInit} from '@angular/core';
import {
  Carpark,
  CarparksFacade,
  ChangeCarpark,
  CarparkConfiguration,
  CarParkResponse, CarparkService
} from "@frontend/administration/carpark/data-access";
import {ActivatedRoute} from "@angular/router";
import {BaseComponent, ObjectMapper} from "@frontend/shared/core";
import {ToastComponent} from "../../../../../../shared/ui/src/lib/material/toast.component";

@Component({
  selector: 'frontend-edit-carpark',
  templateUrl: './edit-carpark.component.html',
  styleUrls: ['./edit-carpark.component.scss'],
  providers: [ToastComponent]
})
export class EditCarparkComponent extends BaseComponent implements OnInit {

  carpark: CarParkResponse
  routedId: string

  constructor(
    private carParkFacade: CarparksFacade,
    private carParkService: CarparkService,
    private route: ActivatedRoute,
    private toast: ToastComponent,
    configuration: CarparkConfiguration
  ) {
    super(configuration)
  }

  ngOnInit(): void {
    // GET allready loaded carpark from the subject observable
    const id = this.route.snapshot.paramMap.get('id')
    this.carParkFacade.carparks$.forEach((carparks: CarParkResponse[]) => {
      carparks.filter((carpark:CarParkResponse) => {
        carpark.carParkId === id ? this.carpark = carpark : null
      })
    })
    //Browser page reload
    if (!this.carpark) {
      this.carParkService.get(id,{}, ['lots','tariff.pricelist']).subscribe((carpark: CarParkResponse) => {
        this.carpark = carpark
      })
    }
  }

  submit() {
    const dto = ObjectMapper.transpose(this.carpark, ChangeCarpark)
    this.carParkFacade.changeCarpark(dto).subscribe((carpark:Carpark) => {
      this.toast.show("Carpark bearbeitet")
    })
  }

  get title() {
    return this.navigation.carpark.edit.title
  }

}
