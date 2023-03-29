import { HttpClientModule } from "@angular/common/http";
import { async, TestBed } from "@angular/core/testing";
import { ReactiveFormsModule } from "@angular/forms";
import { RouterTestingModule } from "@angular/router/testing";
import { of } from "rxjs";
import { LeaseService } from "src/app/service/lease.service";
import { ViewAppliedLeaseComponent } from "./view-applied-lease.component";

describe('ViewAppliedLeaseComponent', () => {
    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                RouterTestingModule,
                ReactiveFormsModule,
                HttpClientModule
            ],
            providers: [LeaseService
            ],
            declarations: [
                ViewAppliedLeaseComponent
            ],
        }).compileComponents();
    }));
    it('should create the app', () => {
        const fixture = TestBed.createComponent(ViewAppliedLeaseComponent);
        const app = fixture.componentInstance;
        expect(app).toBeTruthy();
    });
    it('should invoke ngOnit', () => {
        const fixture = TestBed.createComponent(ViewAppliedLeaseComponent);
        const app = fixture.componentInstance;
        sessionStorage.setItem('roleId', '1');
        let leaseService = TestBed.inject(LeaseService);
        let res = [
            { 'referenceId': 'FLA202320100006', 'customerName': 'Test' }
        ];
        spyOn(leaseService, 'fetchAppliedLeaseHistory').and.returnValue(of(res));
        app.ngOnInit();
        expect(app).toBeTruthy();
    });
    it('should invoke ngOnit for normal User', () => {
        const fixture = TestBed.createComponent(ViewAppliedLeaseComponent);
        const app = fixture.componentInstance;
        sessionStorage.setItem('roleId', '2');
        let leaseService = TestBed.inject(LeaseService);
        let res = [
            { 'referenceId': 'FLA202320100006', 'customerName': 'Test' }
        ];
        spyOn(leaseService, 'fetchAppliedLeaseHistory').and.returnValue(of(res));
        app.ngOnInit();
        expect(app).toBeTruthy();
    });
    it('should invoke updateStatus method', () => {
        const fixture = TestBed.createComponent(ViewAppliedLeaseComponent);
        const app = fixture.componentInstance;
        app.updateStatus('FLA202320100006');
        expect(app).toBeTruthy();
    });
});