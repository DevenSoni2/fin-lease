import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs/internal/Observable";
import { AppConstants } from "../app..constant";
import { LeaseRequest } from "../models/lease-request.model";
import { LeaseUpdateModel } from "../models/lease-update.model";

@Injectable({
    providedIn: 'root'
})
export class LeaseService {
    constructor(private http: HttpClient) {
    }
    getLeaseDetail(referenceId): Observable<LeaseRequest> {
        let url = AppConstants.BACKEND_HOST + '/api/lease/info/' + referenceId;
        return this.http.get<any>(url);
    }
    getUserDet(userId: string): Observable<any> {
        let url = AppConstants.BACKEND_HOST + '/api/lease/fetchProposerDetail/' + userId;
        return this.http.get<any>(url);
    }
    getCustomerDet(customerId: string): Observable<any> {
        let url = AppConstants.BACKEND_HOST + '/api/lease/fetchCustomerDetail?customerId=' + customerId;
        return this.http.get<any>(url);
    }
    applyLease(leaseReq: LeaseRequest) {
        let url = AppConstants.BACKEND_HOST + '/api/lease/apply';
        return this.http.post<any>(url, leaseReq);
    }
    fetchAppliedLeaseHistory(status): Observable<any> {
        let url = AppConstants.BACKEND_HOST + '/api/lease/list?status='+status;
        return this.http.get<any>(url);
    }
    updateLeaseStatus(leaveUpdateModel: LeaseUpdateModel): Observable<any> {
        let url = AppConstants.BACKEND_HOST + '/api/lease/updateLeaseStatus';
        return this.http.put<any>(url, leaveUpdateModel);
    }
    exportExcel(status) {
        let url = AppConstants.BACKEND_HOST + '/api/lease/leaseExcel?status='+status;
        return this.http.get<any>(url, { 'responseType': 'arraybuffer' as 'json' });
    }
}