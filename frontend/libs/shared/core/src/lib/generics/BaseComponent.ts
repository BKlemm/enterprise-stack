import {Subscription} from "rxjs";
import {Component, OnDestroy} from "@angular/core";
import {Configuration} from "../types";

@Component({template:''})
export abstract class BaseComponent implements OnDestroy {

  protected subscription: Subscription;
  protected routedId: string

  protected constructor(private configuration?: Configuration) {}

  public trackBy(index: any, item: any) {
    return index
  }

  public ngOnDestroy(){
    if (this.subscription) {
      console.log('UNSUBSCRIBED')
      this.subscription.unsubscribe()
    }
  }

  get navigation() {
    return this.configuration.navigation
  }

  get filter() {
    return this.configuration.filter
  }

  get displayedColumns() {
    return this.configuration.displayedColumns
  }

  get columns() {
    return this.configuration.columns
  }

  get tabs() {
    return this.configuration.tabs
  }
}
