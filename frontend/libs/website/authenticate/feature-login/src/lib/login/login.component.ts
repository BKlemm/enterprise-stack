import {Component, Input, OnInit, Output, EventEmitter
} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "@frontend/shared/core";
import {Router} from "@angular/router";

@Component({
  selector: 'fe-login-form',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  providers: [AuthService]
})
export class LoginComponent {

  @Input() isLogin: boolean = false;
  @Output() isLoginOpen = new EventEmitter<boolean>()

  loginForm: FormGroup

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private router: Router) {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  login() {
    const val = this.loginForm.value

    if (val.email && val.password) {
      this.authService.login(val.email, val.password)
    }
  }

}
