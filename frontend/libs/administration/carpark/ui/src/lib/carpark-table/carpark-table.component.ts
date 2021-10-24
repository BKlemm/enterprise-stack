import {AfterViewInit, Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {debounceTime, distinctUntilChanged, tap} from "rxjs/operators";
import {fromEvent, merge} from "rxjs";
import {CarparkService, CarparksFacade} from "@frontend/administration/carpark/data-access";

@Component({
  selector: 'adm-carpark-table',
  templateUrl: './carpark-table.component.html',
  styleUrls: ['./carpark-table.component.scss'],
})
export class CarparkTableComponent implements AfterViewInit, OnInit {

  displayedColumns: string[] = ['name', 'iata', 'description', 'tax', 'state'];
  dataSource: CarparksFacade;

  totalSize = 0
  currentPage = 0
  pageSize = 10

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild('input') input!: ElementRef;

  constructor(private carparkService: CarparkService) {}

  ngOnInit() {
    this.dataSource = new CarparksFacade(this.carparkService)
    this.dataSource.load()
  }

  ngAfterViewInit() {
    // server-side search
    fromEvent(this.input.nativeElement,'keyup')
      .pipe(
        debounceTime(150),
        distinctUntilChanged(),
        tap(() => {
          this.paginator.pageIndex = 0;
          this.loadPage();
        })
      )
      .subscribe();
    // reset the paginator after sorting
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);

    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        tap(() => this.loadPage())
      )
      .subscribe();
  }

  loadPage() {
    this.dataSource.load(
      this.input.nativeElement.value,
      this.sort.direction,
      this.paginator.pageIndex,
      this.paginator.pageSize
    )
  }

  handlePage(event) {
    this.currentPage = event.pageIndex
    this.pageSize = event.pageSize
    this.loadPage()
  }
}
