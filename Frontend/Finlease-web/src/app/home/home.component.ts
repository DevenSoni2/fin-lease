import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { ApplicationStorageService } from "../service/application-storage.service";
import { LoginService } from "../service/login.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})

export class HomeComponent implements OnInit{
  roleId: string;
  constructor(private router: Router, private loginService: LoginService, private applicationStorage: ApplicationStorageService) {
  }
  ngOnInit(): void {
    this.roleId = this.applicationStorage.get('roleId');
  }
  homepgae() {
    this.router.navigate(['home/view-applied-lease']);
  }
  logout() {
    this.loginService.logout().subscribe(res => {
      this.applicationStorage.clear();
      sessionStorage.clear();
      this.router.navigate(['login'])
    }, (error => {
      this.applicationStorage.clear();
      this.router.navigate(['login'])
    }
    ));
  }
  applyLease(){
    this.router.navigate(['home/applyLease']);
  }
}