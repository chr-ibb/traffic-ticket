import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { TicketService } from '../../services/ticket.service';
import { Ticket } from '../../models/ticket.model';

@Component({
  selector: 'app-ticket-search',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './ticket-search.component.html',
  styleUrls: ['./ticket-search.component.scss']
})
export class TicketSearchComponent implements OnInit {
  searchForm: FormGroup;
  tickets: Ticket[] = [];
  selectedTicket: Ticket | null = null;
  loading = false;
  error: string | null = null;

  constructor(
    private fb: FormBuilder,
    private ticketService: TicketService
  ) {
    this.searchForm = this.fb.group({
      licensePlate: [''],
      driverLicense: ['']
    }, { validators: this.atLeastOneRequired });
  }

  ngOnInit(): void {}

  atLeastOneRequired(group: FormGroup) {
    const licensePlate = group.get('licensePlate')?.value;
    const driverLicense = group.get('driverLicense')?.value;
    return licensePlate || driverLicense ? null : { atLeastOneRequired: true };
  }

  onSubmit() {
    if (this.searchForm.valid) {
      this.loading = true;
      this.error = null;
      const searchData = this.searchForm.value;

      this.ticketService.searchTickets(searchData).subscribe({
        next: (tickets) => {
          this.tickets = tickets;
          this.loading = false;
        },
        error: (err) => {
          this.error = 'Error searching for tickets. Please try again.';
          this.loading = false;
        }
      });
    }
  }

  selectTicket(ticket: Ticket) {
    this.selectedTicket = ticket;
  }

  closeModal() {
    this.selectedTicket = null;
  }

  payTicket(ticket: Ticket) {
    this.ticketService.payTicket(ticket.ticketNumber).subscribe({
      next: () => {
        const index = this.tickets.findIndex(t => t.ticketNumber === ticket.ticketNumber);
        if (index !== -1) {
          this.tickets[index] = { ...ticket, paid: true };
        }
        this.selectedTicket = null;
      },
      error: (err) => {
        this.error = 'Error processing payment. Please try again.';
      }
    });
  }
} 