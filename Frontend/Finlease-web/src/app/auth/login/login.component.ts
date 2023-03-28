import { Component, EventEmitter, Input, Output } from "@angular/core";
import { FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { LoginRequest } from "src/app/models/login-request.model";
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
  public fb: FormBuilder,){

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
        sessionStorage.setItem("access_token",res.accessToken);
        sessionStorage.setItem("user_id",res.id);
        sessionStorage.setItem("username", res.username);
         this.leaseService.getUserDet(res.id).subscribe(data=>{
          sessionStorage.setItem("roleId",data.roleId);
          this.router.navigate(["home"]);
        },(error=>{
          alert("Error occur in user info loading");
          this.router.navigate(["login"]);
        }
        )) ;
      },(error=>{
        alert("Invalid username or password");
      }
      ));
    }
  }
  
}