import { HttpClientModule } from "@angular/common/http";
import { async, TestBed } from "@angular/core/testing";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { RouterTestingModule } from "@angular/router/testing";
import { of, throwError } from "rxjs";
import { LeaseService } from "src/app/service/lease.service";
import { ApproveLeaseComponent } from "./approve-lease.component";

describe('ApproveLeaseComponent', () => {
    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                RouterTestingModule,
                FormsModule,
                ReactiveFormsModule,
                HttpClientModule
            ],
            providers: [LeaseService,
                {
                    provide: ActivatedRoute,
                    useValue: {
                        params: of({ referenceId: 'FLA' })
                    }
                },
            ],
            declarations: [
                ApproveLeaseComponent
            ],
        }).compileComponents();
    }));

    it('should create the app', () => {
        const fixture = TestBed.createComponent(ApproveLeaseComponent);
        const app = fixture.componentInstance;
        expect(app).toBeTruthy();
    });
    it('should invoke ngOnit', () => {
        const fixture = TestBed.createComponent(ApproveLeaseComponent);
        const app = fixture.componentInstance;
        app.ngOnInit();
        expect(app).toBeTruthy();
    });
    it('test updateAppliedLease method', () => {
        const fixture = TestBed.createComponent(ApproveLeaseComponent);
        const app = fixture.componentInstance;
        let leaseService = TestBed.inject(LeaseService);
        app.referenceId = 'FRA202010100003';
        spyOn(leaseService, 'updateLeaseStatus').and.returnValue(of('Lease updated successfully'));
        app.updateAppliedLease();
        expect(app).toBeTruthy();
    });
    it('test updateAppliedLease method err condition', () => {
        const fixture = TestBed.createComponent(ApproveLeaseComponent);
        const app = fixture.componentInstance;
        let leaseService = TestBed.inject(LeaseService);
        app.referenceId = 'FRA202010100003';
        spyOn(leaseService, 'updateLeaseStatus').and.returnValue(throwError("err occ"));
        app.updateAppliedLease();
        expect(app).toBeTruthy();
    });
    it('test back method', () => {
        const fixture = TestBed.createComponent(ApproveLeaseComponent);
        const app = fixture.componentInstance;
        app.back();
        expect(app).toBeTruthy();
    });
    it('test isNumber Method 1', () => {
        const fixture = TestBed.createComponent(ApproveLeaseComponent);
        const app = fixture.componentInstance;
        let event = { 'which': 56 };
        expect(app.numberOnly(event)).toBeTrue();
    });
    it('test isNumber Method 2', () => {
        const fixture = TestBed.createComponent(ApproveLeaseComponent);
        const app = fixture.componentInstance;
        let event = { 'keyCode': 100 };
        expect(app.numberOnly(event)).toBeFalse();
    });
    it('test updateLeaseApplication method', () => {
        const fixture = TestBed.createComponent(ApproveLeaseComponent);
        const app = fixture.componentInstance;
        app.leaseApprovalForm.controls.otherConditions.setValue('abc');
        app.leaseApprovalForm.controls.comments.setValue('test');
        app.leaseApprovalForm.controls.decision.setValue('Approved');
        app.updateLeaseApplication();
        expect(app.leaseUpdateReq.status).toBe('Approved');
    });

});