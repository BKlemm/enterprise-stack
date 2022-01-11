import {Component, Input} from '@angular/core';

@Component({
  selector: 'ui-expansion-panel',
  templateUrl: './expansion-panel.component.html',
  styleUrls: ['./expansion-panel.component.scss']
})
export class ExpansionPanelComponent {

  @Input() title: string
  @Input() description: any
  @Input() expand: boolean

}
