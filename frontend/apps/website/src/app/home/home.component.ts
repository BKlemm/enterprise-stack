import {Component, OnInit} from '@angular/core';
import {BrowseCarparksFacade, Carpark, CarparkService} from "@frontend/website/availability/data-access";

@Component({
  selector: 'page-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  providers: [
    BrowseCarparksFacade,
    CarparkService
  ]
})
export class HomeComponent implements OnInit {

  carParks: Carpark[] = []

  constructor(private carparkFacade: BrowseCarparksFacade) {}

  ngOnInit(): void {
    this.carparkFacade.load().carparks$.subscribe(carparks => {
      this.carParks = carparks
    })
  }
}
