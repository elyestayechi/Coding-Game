import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';  // Import RouterModule here

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule],  // Add RouterModule here
  templateUrl: './app.component.html',
  styleUrls: []
})
export class AppComponent {
  title = 'my-angular-app';
}
