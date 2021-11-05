import {Component, EventEmitter, Input,Output} from '@angular/core';

interface Column {
  columnDef: string,
  dep?: string,
  header: string,
  condition?: string,
  link?: string,
  linkRef?: string
}

@Component({
  selector: 'adm-ui-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent {

  @Input() displayedColumns: string[]
  @Input() dataSource: any
  @Input() columns: Column[]
  @Input() activeSort: string
  @Input() sortDirection: string
  @Input() totalSize: number
  @Input() currentPage: number
  @Input() filterPlaceholder: string

  @Output() handlePage = new EventEmitter()

  onHandlePage(){
    this.handlePage.emit()
  }
}
