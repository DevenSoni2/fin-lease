import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { LeaseRequest } from "src/app/models/lease-request.model";
import { LeaseUpdateModel } from "src/app/models/lease-update.model";
import { LeaseService } from "src/app/service/lease.service";

@Component({
    selector: 'approve-lease',
    templateUrl: './approve-lease.component.html',
    styleUrls: ['./approve-lease.component.scss']
})

export class ApproveLeaseComponent implements OnInit {
    referenceId: string;
    leaseReq: LeaseRequest;
    leaseUpdateReq: LeaseUpdateModel;
    leaseApprovalForm: FormGroup;

    constructor(public fb: FormBuilder, private route: ActivatedRoute, private leaseService: LeaseService,
        private router: Router) {
        this.leaseApprovalForm = this.fb.group({
            approvalDate: new FormControl({ value: new Date().toISOString().slice(0, -1), disabled: true }),
            approvalAmount: ['', Validators.required],
            interestRate: ['', Validators.required],
            monthlyRepayment: ['', Validators.required],
            residualValue: ['', Validators.required],
            decision: ['', Validators.required],
            otherConditions: ['', [Validators.required, Validators.maxLength(500)]],
            comments: ['', [Validators.required, Validators.maxLength(500)]]
        });
    }
    ngOnInit(): void {
        this.route.params.subscribe(params => {
            this.referenceId = params['referenceId'];
        });
    }
    updateLeaseApplication() {
        this.leaseUpdateReq = new LeaseUpdateModel();
        this.leaseUpdateReq.accountOfficerComment = this.leaseApprovalForm.controls.otherConditions.value;
        this.leaseUpdateReq.comment = this.leaseApprovalForm.controls.comments.value;
        this.leaseUpdateReq.approvalDate = new Date(this.leaseApprovalForm.controls.approvalDate.value);
        this.leaseUpdateReq.status = this.leaseApprovalForm.controls.decision.value;
        this.leaseUpdateReq.leaseReferenceId = this.referenceId;
        this.leaseUpdateReq.updatedBy = sessionStorage.getItem('user_id');
        this.leaseUpdateReq.approvalAmount = this.leaseApprovalForm.controls.approvalAmount.value;
        this.leaseUpdateReq.monthlyRepayment = this.leaseApprovalForm.controls.monthlyRepayment.value;
        this.leaseUpdateReq.interestRate = this.leaseApprovalForm.controls.interestRate.value;
        this.leaseUpdateReq.residualValue = this.leaseApprovalForm.controls.residualValue.value;
        console.log(this.leaseUpdateReq);
        this.updateAppliedLease();
    }
    updateAppliedLease() {
        this.leaseService.updateLeaseStatus(this.leaseUpdateReq).subscribe(res => {
            alert("Lease status updated successfully")
            this.router.navigate(['home/view-applied-lease'])
        }, (error => {
            alert("Error occurs in lease updation");
        }
        ));
    }
    back() {
        this.router.navigate(['home/view-applied-lease']);
    }
    numberOnly(event): boolean {
        const charCode = (event.which) ? event.which : event.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
          return false;
        }
        return true;
    
      }
}