import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AuthGuardService } from "../service/auth.guard";
import { ApplyLeaseComponent } from "./apply-lease/apply-lease.component";
import { ApproveLeaseComponent } from "./approve-lease/approve-lease.component";
import { HomeComponent } from "./home.component";
import { ViewAppliedLeaseComponent } from "./view-applied-lease/view-applied-lease.component";

const routes: Routes = [
    {
        path: '',
        component: HomeComponent,
        children: [
            { path: '', redirectTo: 'view-applied-lease', pathMatch: 'full' },
            { path: 'applyLease', component: ApplyLeaseComponent},
            { path: 'view-applied-lease', component: ViewAppliedLeaseComponent},
            { path: 'approveLease/:referenceId', component: ApproveLeaseComponent, canActivate: [AuthGuardService], data:{roles:'1'}}
          ]
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class HomeRoutingModule { }