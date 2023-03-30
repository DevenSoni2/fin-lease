import { NgModule } from "@angular/core";
import { HomeComponent } from "./home.component";
import { MatFormFieldModule } from '@angular/material/form-field';
import { ReactiveFormsModule } from "@angular/forms";

import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { HomeRoutingModule } from "./home-routing.module";
import { MatGridListModule } from '@angular/material/grid-list';
import { MatDialogModule, MatExpansionModule, MatNativeDateModule, MatOptionModule, MatRippleModule, MatSelectModule } from "@angular/material";
import { MatIconModule } from '@angular/material/icon';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { CommonModule } from "@angular/common";
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { MatTooltipModule } from '@angular/material/tooltip';
import { ApplyLeaseComponent } from "./apply-lease/apply-lease.component";
import { ApproveLeaseComponent } from "./approve-lease/approve-lease.component";
import { ViewAppliedLeaseComponent } from "./view-applied-lease/view-applied-lease.component";
import { AuthGuardService } from "../service/auth.guard";
import { SessionGuardService } from "../service/session.guard.service";

@NgModule({
  declarations: [HomeComponent, ApplyLeaseComponent, ApproveLeaseComponent,
    ViewAppliedLeaseComponent],
  imports: [
    HomeRoutingModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    MatGridListModule,
    MatRippleModule,
    MatIconModule,
    MatDatepickerModule,
    MatNativeDateModule,
    CommonModule,
    MatSnackBarModule,
    MatTableModule,
    MatIconModule,
    MatTooltipModule,
    MatOptionModule,
    MatSelectModule,
    MatExpansionModule,
    MatIconModule,
    MatSnackBarModule
  ],
  providers: [MatDatepickerModule, AuthGuardService, SessionGuardService
  ]
})
export class HomeModule {

}