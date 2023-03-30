import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { Observable } from "rxjs/internal/Observable";
import { ApplicationStorageService } from "./application-storage.service";

@Injectable()
export class AuthGuardService implements CanActivate {
    constructor(private route: Router, private applicationStorage: ApplicationStorageService) {
    }
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot)
        : Observable<boolean> | Promise<boolean> | boolean {
        let requiredRole = route.data.roles as string;
        let userRoleId = this.applicationStorage.get('roleId');
        if (requiredRole == userRoleId) {
            return true;
        } else {
            this.route.navigate(["home"])
            return false;
        }
    }
}