import { Component, ViewChild } from "@angular/core";
import { FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { MatAccordion, MatSnackBar } from "@angular/material";
import { Router } from "@angular/router";
import { LeaseRequest } from "src/app/models/lease-request.model";
import { LeaseService } from "src/app/service/lease.service";

@Component({
  selector: 'app-lease',
  templateUrl: './apply-lease.component.html',
  styleUrls: ['./apply-lease.component.scss']
})
export class ApplyLeaseComponent {
  leaseForm: FormGroup;
  panelOpenState = false;
  leaseReq: LeaseRequest;
  isExpanded = true;
  firstPanel = true;
  isApplied = false;
  disableSubmitBtn = false;
  @ViewChild('accordion', { static: true }) Accordion: MatAccordion;

  constructor(public fb: FormBuilder, private leaseService: LeaseService,
    private router: Router) {
    this.leaseForm = this.fb.group({
      createdBy: [{ value: sessionStorage.getItem("username"), disabled: true }],
      referenceId: [''],
      createdTime: new FormControl({ value: new Date().toISOString().slice(0, -1), disabled: true }),
      customerNumber: ['', Validators.required],
      customerAccountNumber: ['', Validators.required],
      customerPhoneNumber: ['', Validators.required],
      customerName: ['', Validators.required],
      assetMake: ['', Validators.required],
      assetModel: ['', Validators.required],
      assetDeliveryDate: [new Date(), Validators.required],
      assetPurpose: ['', Validators.required],
      requiredAmount: ['', Validators.required],
      leasePeriod: ['', Validators.required],
      monthlyIncome: ['', Validators.required],
      otherIncome: ['', Validators.required],
      totalIncome: ['', Validators.required],
      totalCommitmentMCB: ['', Validators.required],
      totalCommitmentOther: ['', Validators.required],
      creditTurnover: ['', Validators.required],
      debitTurnover: ['', Validators.required],
      leaseType: ['', Validators.required],
      setupParameter: ['', Validators.required],
      commentRecommendation: ['', Validators.required],
      proposerUserId: ['', Validators.required],
      propsedUserName: [{ value: '', disabled: true }, Validators.required],
      proposedUserBU: [{ value: '', disabled: true }, Validators.required],
      title: ['', Validators.required]
    });
  }
  applyLease() {
    console.log(this.leaseForm.value);
    this.leaseReq = new LeaseRequest();
    this.leaseReq.monthlyIncome = this.leaseForm.controls.monthlyIncome.value;
    this.leaseReq.totalIncome = this.leaseForm.controls.totalIncome.value;
    this.leaseReq.otherIncome = this.leaseForm.controls.otherIncome.value;
    this.leaseReq.totalCommitmentMCB = this.leaseForm.controls.totalCommitmentMCB.value;
    this.leaseReq.totalCommitmentOther = this.leaseForm.controls.totalCommitmentOther.value;
    this.leaseReq.creditTurnover = this.leaseForm.controls.creditTurnover.value;
    this.leaseReq.debitTurnover = this.leaseForm.controls.debitTurnover.value;
    this.leaseReq.setUpParameter = this.leaseForm.controls.setupParameter.value;
    this.leaseReq.commentRecommendation = this.leaseForm.controls.commentRecommendation.value;
    this.leaseReq.assetDeliveryDate = new Date(this.leaseForm.controls.assetDeliveryDate.value);
    this.leaseReq.assetMake = this.leaseForm.controls.assetMake.value;
    this.leaseReq.assetModel = this.leaseForm.controls.assetModel.value;
    this.leaseReq.assetPurpose = this.leaseForm.controls.assetPurpose.value;
    this.leaseReq.createdBy = sessionStorage.getItem('user_id');
    this.leaseReq.createdTime = this.leaseForm.controls.createdTime.value;
    this.leaseReq.customerAccountNumber = this.leaseForm.controls.customerAccountNumber.value;
    this.leaseReq.customerNumber = this.leaseForm.controls.customerNumber.value;
    this.leaseReq.leasePeriodInMonth = this.leaseForm.controls.leasePeriod.value;
    this.leaseReq.leaseType = this.leaseForm.controls.leaseType.value;
    this.leaseReq.phoneNumber = this.leaseForm.controls.customerPhoneNumber.value;
    this.leaseReq.proposerUserId = this.leaseForm.controls.proposerUserId.value;
    this.leaseReq.requiredAmount = this.leaseForm.controls.requiredAmount.value;
    this.leaseReq.customerName = this.leaseForm.controls.customerName.value;
    this.saveApplyLease();
  }
  onProposedUserSelect() {
    this.leaseService.getCustomerDet(this.leaseForm.controls.proposerUserId.value).subscribe(res => {
      this.leaseForm.controls.propsedUserName.setValue(res.userName);
      this.leaseForm.controls['propsedUserName'].disable();

      this.leaseForm.controls.proposedUserBU.setValue(res.buisnessUnit);
      this.leaseForm.controls['proposedUserBU'].disable();
    }, (error => {
      this.leaseForm.controls.proposerUserId.reset();
      this.leaseForm.controls.propsedUserName.reset();
      this.leaseForm.controls.proposedUserBU.reset();
    }
    ));
  }
  saveApplyLease() {
    this.leaseService.applyLease(this.leaseReq).subscribe(res => {
      alert("FinLease applied successfully with referenceId: "+ res.referenceId);
      this.isApplied = true;
      this.leaseForm.controls.referenceId.setValue(res.referenceId);
      this.leaseForm.controls['referenceId'].disable();
      this.disableSubmitBtn = true;
    }, (error => {
      alert("Error occurs in lease application");
    }
    ));
  }
  back() {
    this.router.navigate(['home/view-applied-lease']);
  }
  print() {
    window.print();
  }
  numberOnly(event): boolean {
    const charCode = (event.which) ? event.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;

  }
}