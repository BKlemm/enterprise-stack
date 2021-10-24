import {AfterViewInit, Component, ElementRef, ViewChild} from "@angular/core";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";

@Component({
  template: ''
})
export abstract class BaseTableComponent implements AfterViewInit {

  dataSource: any;

  totalSize = 0
  currentPage = 0
  pageSize = 10

  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;
  @ViewChild('input') public input: ElementRef;

  public ngAfterViewInit(): void {
    // server-side search
    /**fromEvent(this.input.nativeElement,'keyup')
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
      .subscribe();*/
  }

  public loadPage() {
    this.dataSource.load(
      this.input.nativeElement.value,
      this.sort.direction,
      this.paginator.pageIndex,
      this.paginator.pageSize
    )
  }

  public handlePage(event) {
    this.currentPage = event.pageIndex
    this.pageSize = event.pageSize
    this.loadPage()
  }


}
