import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'web-default-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {

  siteLanguage?: string = 'Deutsch'
  siteLocale!: string

  isLogin = false;
  isRegister = false;

  languages = [
    {code: 'de', label: 'Deutsch'},
    {code: 'en', label: 'English'}
  ]

  ngOnInit(): void {
    this.siteLocale = window.location.pathname.split('/')[1]
    this.siteLanguage = this.languages.find(lang => lang.code === this.siteLocale)?.label
  }

  toogleLogin() {
    this.isLogin = !this.isLogin
  }

  toogleRegister() {
    this.isRegister = !this.isRegister
  }

}
