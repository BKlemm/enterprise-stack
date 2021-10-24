import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";

interface Page<T> {
  title: string,
  link: string
}

@Component({
  selector: 'ui-breadcrumb',
  templateUrl: './breadcrumb.component.html',
  styleUrls: ['./breadcrumb.component.scss']
})
export class BreadcrumbComponent implements OnInit {

  pages = new Array<string>()
  @Input() currentPage: string = ''

  constructor(private router: Router) {
    this.pages.push(...router.url.split('/').slice(1))
  }

  ngOnInit(): void {
  }

}
