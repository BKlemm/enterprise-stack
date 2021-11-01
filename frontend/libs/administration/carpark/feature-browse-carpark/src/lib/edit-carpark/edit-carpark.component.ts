import {Component,OnInit} from '@angular/core';
import {TABMENU} from "../tab.menu";
import {Carpark, CarparksFacade, ChangeCarpark} from "@frontend/administration/carpark/data-access";
import {ActivatedRoute} from "@angular/router";
import {BaseComponent, ObjectMapper} from "@frontend/shared/core";

@Component({
  selector: 'frontend-edit-carpark',
  templateUrl: './edit-carpark.component.html',
  styleUrls: ['./edit-carpark.component.scss']
})
export class EditCarparkComponent extends BaseComponent implements OnInit {

  tabs = TABMENU
  //TODO: localize
  title = 'Parkplatz bearbeiten'

  carpark: Carpark
  routedId: string

  constructor(
    private carParkFacade: CarparksFacade,
    private route: ActivatedRoute
  ) {super()}

  ngOnInit(): void {
    // GET allready loaded carpark from the subject observable
    const id = this.route.snapshot.paramMap.get('id')
    this.carParkFacade.carparks$.forEach((carparks: Carpark[]) => {
      carparks.forEach((carpark:Carpark) => {
        if (carpark.carParkId === id) {
          this.carpark = carpark
        }
      })
    })
    //Browser page reload
    if (!this.carpark) {
      this.carParkFacade.loadById(id)
    }

  }

  submit() {
    const dto = ObjectMapper.transpose(this.carpark, ChangeCarpark)
    this.carParkFacade.update(dto)
  }

}
