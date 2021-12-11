import {AfterViewInit, Component, ElementRef, EventEmitter, Input, Output, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {fromEvent, merge} from "rxjs";
import {debounceTime, distinctUntilChanged, tap} from "rxjs/operators";
import {Column} from "@frontend/shared/core";

@Component({
  selector: 'adm-ui-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements AfterViewInit {

  @Input() displayedColumns: string[]
  @Input() dataSource: any
  @Input() columns: Column[]
  @Input() activeSort: string
  @Input() sortDirection: string
  @Input() totalSize: number
  @Input() currentPage: number
  @Input() filterPlaceholder: string
  @Input() enableSpinner: boolean = false

  @ViewChild(MatPaginator, {static: true}) paginator!: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild('input') input: ElementRef;

  clickedRows = new Set<any>();
  filter: string = ''

  ngAfterViewInit() {
    this.dataSource.sort = this.sort
    this.dataSource.paginator = this.paginator
    this.dataSource.input = this.input
    // server-side search
    fromEvent(this.input.nativeElement,'keyup')
      .pipe(
        debounceTime(150),
        distinctUntilChanged(),
        tap(() => {
          console.log("UI-Table Searching...")
          this.paginator.pageIndex = 0;
          this.loadPage();
        })
      )
      .subscribe();
    // reset the paginator after sorting
    this.sort.sortChange.subscribe(() => { this.paginator.pageIndex = 0 });
    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        tap(() => {
          this.activeSort = this.sort.active
          this.sortDirection = this.sort.direction
          console.log("UI-Table Sorting..." + this.activeSort)
          this.loadPage()
        })
      ).subscribe();
  }
  loadPage() {
    this.dataSource.load(
      this.sort.active,
      this.sort.direction,
      this.input.nativeElement.value,
      this.paginator.pageIndex,
      this.paginator.pageSize
    )
  }
  handlePage(event) {
    this.paginator.pageIndex = event.pageIndex
    this.paginator.pageSize = event.pageSize
    this.loadPage()
  }
}
