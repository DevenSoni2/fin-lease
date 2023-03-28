import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { Observable } from "rxjs/internal/Observable";

@Injectable()
export class AuthGuardService implements CanActivate {
    constructor(private route: Router) {
    }
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot)
        : Observable<boolean> | Promise<boolean> | boolean {
        let requiredRole = route.data.roles as string;
        let userRoleId = sessionStorage.getItem('roleId');
        if (requiredRole == userRoleId) {
            return true;
        } else {
            this.route.navigate(["home"])
            return false;
        }
    }
}