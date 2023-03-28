import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { LeaseService } from "src/app/service/lease.service";

@Component({
    selector: 'view-applied-lease',
    templateUrl: './view-applied-lease.component.html',
    styleUrls: ['./view-applied-lease.component.scss']
})

export class ViewAppliedLeaseComponent implements OnInit {
    leaseList: any;
    closedleaseList: any;
    displayedColumns: string[] = ['referenceId', 'createdBy', 'buisnessUnit', 'customerName', 'customerNumber', 'createdTime', 'leaseType', 'assetMake', 'assetModel', 'status'];
    approvedDisplayedColumns: string[] = ['referenceId', 'createdBy', 'buisnessUnit', 'customerName', 'customerNumber', 'createdTime', 'leaseType', 'assetMake', 'assetModel', 'approvalUserId', 'status'];
    roleId: any;
    constructor(private leaseService: LeaseService, private router: Router) { }

    ngOnInit(): void {
        this.roleId = sessionStorage.getItem("roleId");
        this.fetchList();
    }
    /**
     * fetch list
     */
    fetchList() {
        this.leaseService.fetchAppliedLeaseHistory("Open").subscribe(data => {
            this.leaseList = data;
        })
        if (this.roleId == 1) {
            this.leaseService.fetchAppliedLeaseHistory("Closed").subscribe(result => {
                this.closedleaseList = result;
            });
        }
    }
    downloadExcel(status) {
        this.leaseService.exportExcel(status).subscribe(res => {
            this.downloadFile(res);
        })
    }
    downloadFile(data) {
        const blob = new Blob([data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
        const url = window.URL.createObjectURL(blob);
        window.open(url);
    }
    /**
     * Update status
     * @param referenceId 
     */
    updateStatus(referenceId) {
        this.router.navigate(['home/approveLease', referenceId])
    }
}