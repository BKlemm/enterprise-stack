import {Component, Input, OnInit, Output, EventEmitter
} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "@frontend/shared/core";
import {ActivatedRoute, Router} from "@angular/router";
import {first} from "rxjs/operators";

@Component({
  selector: 'fe-login-form',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  providers: [AuthService]
})
export class LoginComponent {

  @Input() isLogin = false;
  @Output() isLoginOpen = new EventEmitter<boolean>()
  @Output() isAuthenticated = new EventEmitter<boolean>()

  loginForm: FormGroup

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private activeRoute: ActivatedRoute
  ) {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    })

    if(this.router.url === '/logout') {
      this.logout()
    }
  }

  login() {
    const val = this.loginForm.value

    if (this.loginForm.invalid) {
      return
    }

    if (val.email && val.password) {
      this.authService.login(val.email, val.password).pipe(first()).subscribe(
        data => {
          this.isAuthenticated.emit(true)
          this.router.navigate(['/'])
        }
      )
    }
  }

  logout() {
    this.authService.logout()
    this.router.navigate(['login'])
  }

}
