import { Component, OnInit } from "@angular/core";
import { MatTableDataSource } from "@angular/material";
import { Router } from "@angular/router";
import { ApplicationStorageService } from "src/app/service/application-storage.service";
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
    constructor(private leaseService: LeaseService, private router: Router, private applicationStorage: ApplicationStorageService) { }

    ngOnInit(): void {
        this.roleId = this.applicationStorage.get("roleId");
        this.fetchList();
    }
    applyFilter1(filterValue: any) {
        filterValue = filterValue.trim();
        filterValue = filterValue.toLowerCase();
        this.leaseList.filter = filterValue;
    }
    applyFilter2(filterValue: any) {
        filterValue = filterValue.trim();
        filterValue = filterValue.toLowerCase();
        this.closedleaseList.filter = filterValue;
    }
    /**
     * fetch list
     */
    fetchList() {
        this.leaseService.fetchAppliedLeaseHistory("Open").subscribe(data => {
            this.leaseList = data;
            this.leaseList = new MatTableDataSource(data);
        })
        if (this.roleId == 1) {
            this.leaseService.fetchAppliedLeaseHistory("Closed").subscribe(result => {
                this.closedleaseList = result;
                this.closedleaseList = new MatTableDataSource(result);
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