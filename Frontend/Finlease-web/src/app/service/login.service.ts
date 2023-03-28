import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AppConstants } from "../app..constant";
import { LoginRequest } from "../models/login-request.model";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(private http: HttpClient) {

  }
  login(request: LoginRequest): Observable<any> {
    let url = AppConstants.BACKEND_HOST + '/api/auth/signin';
    return this.http.post<any>(url, request);
  }
  logout(): Observable<any> {
    let url = AppConstants.BACKEND_HOST + '/api/lease/signout';
    return this.http.get<any>(url);
  }


}