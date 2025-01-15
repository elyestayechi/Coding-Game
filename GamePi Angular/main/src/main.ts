import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';  // Your app configuration file
import { AppComponent } from './app/app.component';  // Standalone AppComponent

// Bootstrap the standalone AppComponent with configuration
bootstrapApplication(AppComponent, appConfig).catch((err) =>
  console.error(err)
);
