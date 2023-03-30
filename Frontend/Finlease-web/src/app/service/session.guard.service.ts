import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { Observable } from "rxjs";
import { ApplicationStorageService } from "./application-storage.service";
import { LoginService } from "./login.service";

@Injectable()
export class SessionGuardService implements CanActivate {
    constructor(private route: Router, private applicationStorage: ApplicationStorageService,
        private loginService: LoginService) {
    }
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot)
        : Observable<boolean> | Promise<boolean> | boolean {
        return this.isSessionValid();
    }

    isSessionValid(): Observable<boolean> {
        return Observable.create(observer => {
            let refreshToken = this.applicationStorage.get('refreshToken');
            if (refreshToken == undefined || refreshToken == '' || refreshToken == null) {
                let sessionRefreshToken = sessionStorage.getItem('refreshToken');
                if (sessionRefreshToken != undefined && sessionRefreshToken != null && sessionRefreshToken != '') {
                    let req = { 'refreshToken': sessionRefreshToken };
                    this.loginService.refreshToken(req).subscribe(data => {
                        this.applicationStorage.set('accessToken', data.accessToken);
                        this.applicationStorage.set('userId', data.id);
                        this.applicationStorage.set('username', data.username)
                        this.applicationStorage.set('refreshToken', data.refreshToken);
                        this.applicationStorage.set('roleId', data.roleId);
                        return observer.next(true);
                    }, error => {
                        alert("Session expired")
                        this.route.navigate(['login']);
                        return observer.next(false);
                    })

                } else {
                    alert("No active Session found")
                    this.route.navigate(['login']);
                    return observer.next(false);
                }

            } else {
                if (this.isTokenExpired(this.applicationStorage.get('accessToken'))) {
                    observer.next(true);
                } else {
                    let req = { 'refreshToken': this.applicationStorage.get('refreshToken') };
                    this.loginService.refreshToken(req).subscribe(data => {
                        this.applicationStorage.set('accessToken', data.accessToken);
                        this.applicationStorage.set('userId', data.id);
                        this.applicationStorage.set('username', data.username)
                        this.applicationStorage.set('refreshToken', data.refreshToken);
                        this.applicationStorage.set('roleId', data.roleId);
                        return observer.next(true);
                    }, error => {
                        alert("Session expired")
                        this.route.navigate(['login']);
                        return observer.next(false);
                    })
                }
            }
        })
    }
    private isTokenExpired(token: string) {
        const expiry = (JSON.parse(atob(token.split('.')[1]))).exp;
        console.log(expiry * 1000);
        console.log(Date.now())
        console.log(expiry * 1000 > Date.now());
        return expiry * 1000 > Date.now();
    }
    
}