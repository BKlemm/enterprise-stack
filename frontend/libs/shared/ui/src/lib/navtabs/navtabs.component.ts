import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'ui-navtabs',
  templateUrl: './navtabs.component.html',
  styleUrls: ['./navtabs.component.scss']
})
export class NavtabsComponent implements OnInit {

  @Input() title: string = ''
  @Input() tabs: Array<object> = new Array<object>({title: '', link: ''})
  @Input() createLink: string|boolean = 'create'

  constructor() { }

  ngOnInit(): void {
  }

}
