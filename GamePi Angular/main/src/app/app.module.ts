import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';  // Add RouterModule for routing
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { ToastrModule } from 'ngx-toastr';
import { NgxEchartsModule } from 'ngx-echarts';
// Import components
import { BlankComponent } from "./layouts/blank/blank.component";
import { FullComponent } from "./layouts/full/full.component";
import { AppChipsComponent } from "./pages/ui-components/chips/chips.component";
import { AppFormsComponent } from "./pages/ui-components/forms/forms.component";
import { StarterComponent } from "./pages/starter/starter.component";
import { ShowcomapnyComponent } from "./pages/showcomapny/showcomapny.component";
import { SigntogameComponent } from "./pages/signtogame/signtogame.component";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { FormsModule,ReactiveFormsModule } from "@angular/forms";

import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';


// Import the SamplePageModule
import { SamplePageModule } from './pages/extra/sample-page/sample-page.module';

@NgModule({
  declarations: [
   
  ],
  imports: [
    BrowserModule,
    NgbModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    HttpClientModule,  // Import HttpClientModule here
    RouterModule.forRoot([]),  // Add routing module if needed
    SamplePageModule,
    BlankComponent,
    FullComponent,
    AppChipsComponent,
    NgxEchartsModule.forRoot({
      echarts: () => import('echarts'),
    }),
    AppFormsComponent,
    MatProgressSpinnerModule,
    StarterComponent  // Import SamplePageModule here
  ],
  providers: [],
})
export class AppModule { }
