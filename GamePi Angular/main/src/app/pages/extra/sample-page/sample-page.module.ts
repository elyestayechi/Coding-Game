// src/app/pages/extra/sample-page/sample-page.module.ts
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppSamplePageComponent } from './sample-page.component';  // Adjust path if needed

@NgModule({
  declarations: [AppSamplePageComponent],  // Declare the component here
  imports: [CommonModule],  // Import necessary modules like CommonModule
  exports: [AppSamplePageComponent]  // Export if you want to use this component elsewhere
})
export class SamplePageModule {}
