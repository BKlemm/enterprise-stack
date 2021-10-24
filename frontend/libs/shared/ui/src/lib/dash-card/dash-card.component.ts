import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'ui-dash-card',
  templateUrl: './dash-card.component.html',
  styleUrls: ['./dash-card.component.scss']
})
export class DashCardComponent implements OnInit {

  @Input() title: string
  @Input() trend: string
  @Input() icon: string
  @Input() amount: string

  constructor() { }

  ngOnInit(): void {
  }

}
