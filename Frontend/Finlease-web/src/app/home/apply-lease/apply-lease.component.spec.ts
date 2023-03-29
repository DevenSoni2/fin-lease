import { HttpClientModule } from "@angular/common/http";
import { async, TestBed } from "@angular/core/testing";
import { ReactiveFormsModule } from "@angular/forms";
import { MatExpansionModule } from "@angular/material";
import { BrowserAnimationsModule, NoopAnimationsModule } from "@angular/platform-browser/animations";
import { RouterTestingModule } from "@angular/router/testing";
import { of, throwError } from "rxjs";
import { LeaseService } from "src/app/service/lease.service";
import { ApplyLeaseComponent } from "./apply-lease.component";

describe('ApplyLeaseComponent', () => {
    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                RouterTestingModule,
                ReactiveFormsModule,
                HttpClientModule,
                MatExpansionModule,
                NoopAnimationsModule
            ],
            providers: [LeaseService
            ],
            declarations: [
                ApplyLeaseComponent
            ],
        }).compileComponents();
    }));
    it('test back method', () => {
        const fixture = TestBed.createComponent(ApplyLeaseComponent);
        const app = fixture.componentInstance;
        app.back();
        expect(app).toBeTruthy();
    });
    it('test isNumber Method 1', () => {
        const fixture = TestBed.createComponent(ApplyLeaseComponent);
        const app = fixture.componentInstance;
        let event = { 'which': 56 };
        expect(app.numberOnly(event)).toBeTrue();
    });
    it('test isNumber Method 2', () => {
        const fixture = TestBed.createComponent(ApplyLeaseComponent);
        const app = fixture.componentInstance;
        let event = { 'keyCode': 100 };
        expect(app.numberOnly(event)).toBeFalse();
    });
    it('test applyLease method throw error', () => {
        const fixture = TestBed.createComponent(ApplyLeaseComponent);
        const app = fixture.componentInstance;
        let leaseService = TestBed.inject(LeaseService);
        spyOn(leaseService, 'applyLease').and.returnValue(throwError("err occ"));
        app.leaseForm.controls.monthlyIncome.setValue('1000');
        app.leaseForm.controls.assetDeliveryDate.setValue(new Date());
        app.leaseForm.controls.customerAccountNumber.setValue('232143');
        app.applyLease();
        expect(app.leaseReq.customerAccountNumber).toBe('232143');
    });
    it('test applyLease method', () => {
        const fixture = TestBed.createComponent(ApplyLeaseComponent);
        const app = fixture.componentInstance;
        let leaseService = TestBed.inject(LeaseService);
        let res = {'referenceId':'FLA202020100006'};
        spyOn(leaseService, 'applyLease').and.returnValue(of(res));
        app.leaseForm.controls.monthlyIncome.setValue('1000');
        app.leaseForm.controls.assetDeliveryDate.setValue(new Date());
        app.leaseForm.controls.customerAccountNumber.setValue('232143');
        app.applyLease();
        //app.print();
        expect(app.leaseReq.customerAccountNumber).toBe('232143');
    });
    it('test onProposedUserSelect method throw error', () => {
        const fixture = TestBed.createComponent(ApplyLeaseComponent);
        const app = fixture.componentInstance;
        let leaseService = TestBed.inject(LeaseService);
        spyOn(leaseService, 'getCustomerDet').and.returnValue(throwError("err occ"));
        app.leaseForm.controls.proposerUserId.setValue('CUS_0001');
        app.leaseForm.controls.assetDeliveryDate.setValue(new Date());
        app.leaseForm.controls.customerAccountNumber.setValue('232143');
        app.onProposedUserSelect();
        expect(app).toBeTruthy();
    });
    it('test onProposedUserSelect', () => {
        const fixture = TestBed.createComponent(ApplyLeaseComponent);
        const app = fixture.componentInstance;
        let leaseService = TestBed.inject(LeaseService);
        let res = {'userName':'CUS1', 'buisnessUnit':'gh'};
        spyOn(leaseService, 'getCustomerDet').and.returnValue(of(res));
        app.leaseForm.controls.proposerUserId.setValue('CUS_0001');
        app.leaseForm.controls.assetDeliveryDate.setValue(new Date());
        app.leaseForm.controls.customerAccountNumber.setValue('232143');
        app.onProposedUserSelect();
        expect(app).toBeTruthy();
    });
});