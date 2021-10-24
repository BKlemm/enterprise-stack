import {Subscription} from "rxjs";
import {Component, OnDestroy} from "@angular/core";

@Component({template:''})
export abstract class BaseComponent implements OnDestroy {

  protected subscription: Subscription;
  protected routedId: string

  public trackBy(index: any, item: any) {
      return index
  }

  public ngOnDestroy(){
    if (this.subscription) {
      console.log('UNSUBSCRIBED')
      this.subscription.unsubscribe()
    }
  }
}
