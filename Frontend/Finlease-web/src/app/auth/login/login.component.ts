import { Component, EventEmitter, Input, Output } from "@angular/core";
import { FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { LoginRequest } from "src/app/models/login-request.model";
import { ApplicationStorageService } from "src/app/service/application-storage.service";
import { LeaseService } from "src/app/service/lease.service";
import { LoginService } from "src/app/service/login.service";

@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})

export class LoginComponent {
  request= new LoginRequest();
  show: boolean = false;
constructor(private _loginService: LoginService,private router: Router, private leaseService: LeaseService,
  public fb: FormBuilder,private applicationStorage: ApplicationStorageService){

}
// click event function toggle
password() {
  this.show = !this.show;
}
form: FormGroup = this.fb.group({
    username: [null, [Validators.required]],
    password: [null, [Validators.required]],
  });

  submit() {
    if (this.form.valid) {
      this.request.username = this.form.value.username;
      this.request.password = this.form.value.password;
      this._loginService.login(this.request).subscribe(res=>{
          this.applicationStorage.set('accessToken', res.accessToken);
          this.applicationStorage.set('userId', res.id);
          this.applicationStorage.set('username', res.username)
          this.applicationStorage.set('refreshToken', res.refreshToken);
          sessionStorage.setItem('refreshToken', res.refreshToken )
          this.applicationStorage.set('roleId',res.roleId);
          this.router.navigate(["home"]);
      },(error=>{
        alert("Invalid username or password");
      }
      ));
    }
  }
  
}