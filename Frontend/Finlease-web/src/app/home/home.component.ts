import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { LoginService } from "../service/login.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})

export class HomeComponent {
  constructor(private router: Router, private loginService: LoginService) {
  }
  homepgae() {
    this.router.navigate(['home/view-applied-lease']);
  }
  logout() {
    this.loginService.logout().subscribe(res => {
      sessionStorage.clear();
      this.router.navigate(['login'])
    }, (error => {
      sessionStorage.clear();
      this.router.navigate(['login'])
    }
    ));
  }
  applyLease(){
    this.router.navigate(['home/applyLease']);
  }
}