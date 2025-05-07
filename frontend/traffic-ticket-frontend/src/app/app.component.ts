import { Component } from '@angular/core';
import { TicketSearchComponent } from './components/ticket-search/ticket-search.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [TicketSearchComponent],
  template: `
    <div class="app-container">
      <header>
        <h1>Traffic Ticket Management System</h1>
      </header>
      <main>
        <app-ticket-search></app-ticket-search>
      </main>
    </div>
  `,
  styles: [`
    .app-container {
      max-width: 1200px;
      margin: 0 auto;
      padding: 20px;
    }
    header {
      text-align: center;
      margin-bottom: 30px;
    }
    h1 {
      color: #333;
    }
  `]
})
export class AppComponent {
  title = 'Traffic Ticket Management System';
}
