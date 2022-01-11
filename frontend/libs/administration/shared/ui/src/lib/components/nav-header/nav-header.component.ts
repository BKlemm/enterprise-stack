import {Component, Input, Output, EventEmitter} from '@angular/core';
import {SearchBase, SearchComponent} from "@appbaseio/searchbase";
import {from, Observable, of} from "rxjs";
import {distinctUntilChanged, map, switchMap} from "rxjs/operators";
import {MatAutocompleteSelectedEvent} from "@angular/material/autocomplete";


@Component({
  selector: 'adm-nav-header',
  templateUrl: './nav-header.component.html',
  styleUrls: ['./nav-header.component.scss']
})
export class NavHeaderComponent {

  @Input() isSidebarOpen = false
  @Input() isMessagebarOpen = false
  @Output() sidebarClicked = new EventEmitter<boolean>()
  @Output() messagebarClicked = new EventEmitter<boolean>()

  searchBase: SearchBase
  searchComp: SearchComponent
  suggestions: Observable<any[]>;

  constructor() {
    this.searchBase = new SearchBase({
      index: 'carparks',
      url: 'http://localhost:9200',
      credentials: '1293478129038',
      headers: {
        "Access-Control-Allow-Origin": "*"
      },
    })
    this.searchComp = this.searchBase.register('search-component', {
      dataField: [
        {
          field: 'description',
          weight: 1
        },
        {
          field: 'description.keyword',
          weight: 1
        },
        {
          field: 'description.search',
          weight: 0.1
        },
        {
          field: 'language',
          weight: 2
        },
        {
          field: 'language.keyword',
          weight: 2
        },
        {
          field: 'language.search',
          weight: 0.2
        },
        {
          field: 'name',
          weight: 5
        },
        {
          field: 'name.keyword',
          weight: 5
        },
        {
          field: 'name.search',
          weight: 0.5
        },
        {
          field: 'owner',
          weight: 1
        },
        {
          field: 'owner.keyword',
          weight: 1
        },
        {
          field: 'owner.search',
          weight: 0.1
        }
      ],
      // Source filtering to improve search latency
      includeFields: [
        'name', 'description', 'owner', 'fullname', 'language', 'topics'
      ],
      size: 5,

    });
  }

  toogleSidebar() {
    this.isSidebarOpen = !this.isSidebarOpen
  }

  toogleMessageBar() {
    this.isMessagebarOpen = !this.isMessagebarOpen
  }

  handleInput(e) {
    this.setSuggestions(e.target.value);
  };

  setSuggestions(value) {
    // If value is empty then don't fetch suggestions
    if(!value) {
      this.searchComp.setValue('', {
        triggerDefaultQuery: false,
        triggerCustomQuery: false,
      });
    } else {
      // Update suggestions when value gets changed
      this.suggestions = of(value).pipe(
        distinctUntilChanged(),
        switchMap(val => {
          this.searchComp.setValue(val, {
            triggerDefaultQuery: false,
            triggerCustomQuery: false,
          });
          return from(this.searchComp.triggerDefaultQuery())
            .pipe(
              map(() => this.searchComp.suggestions))
        })
      )
    }
  }

  handleOptionSelect(selectedOption: MatAutocompleteSelectedEvent) {
    this.searchComp.setValue(selectedOption.option.value, {
      triggerCustomQuery: true, // to update results
      triggerDefaultQuery: true // to update suggestions
    })
  }

}
