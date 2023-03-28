import { NgModule } from "@angular/core";
import { AuthRoutingModule } from "./auth-routing.module";
import { AuthComponent } from "./auth.component";
import { LoginComponent } from "./login/login.component";
import { MatFormFieldModule  } from '@angular/material/form-field';
import { ReactiveFormsModule } from "@angular/forms";

import { MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import { MatIconModule } from "@angular/material";

@NgModule({
    declarations: [
      AuthComponent,
      LoginComponent
    ],
    imports: [
      AuthRoutingModule,
      MatFormFieldModule,
      ReactiveFormsModule,
      MatCardModule,
      MatInputModule,
      MatButtonModule,
      MatIconModule
    ],
  })
export class AuthModule {

}