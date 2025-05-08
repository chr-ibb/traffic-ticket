import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { TicketService, Ticket } from '../../services/ticket.service';

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
  selectedSearchMethod: 'ticketNumber' | 'driverLicense' | 'licensePlate' | null = null;
  hasSearched = false;
  paymentSuccess = false;

  constructor(
    private fb: FormBuilder,
    private ticketService: TicketService
  ) {
    this.searchForm = this.fb.group({
      ticketNumber: [''],
      driverLicense: [''],
      licensePlate: ['']
    });
  }

  ngOnInit(): void {}

  selectSearchMethod(method: 'ticketNumber' | 'driverLicense' | 'licensePlate'): void {
    this.selectedSearchMethod = method;
    this.searchForm.get(method)?.setValidators([Validators.required]);
    this.searchForm.get(method)?.updateValueAndValidity();
    this.hasSearched = false;
  }

  clearSearchMethod(): void {
    if (this.selectedSearchMethod) {
      this.searchForm.get(this.selectedSearchMethod)?.clearValidators();
      this.searchForm.get(this.selectedSearchMethod)?.updateValueAndValidity();
      this.searchForm.get(this.selectedSearchMethod)?.setValue('');
    }
    this.selectedSearchMethod = null;
    this.tickets = [];
    this.error = null;
    this.hasSearched = false;
  }

  getSearchMethodLabel(): string {
    switch (this.selectedSearchMethod) {
      case 'ticketNumber':
        return 'Ticket Number';
      case 'driverLicense':
        return 'Driver\'s License Number';
      case 'licensePlate':
        return 'License Plate Number';
      default:
        return '';
    }
  }

  getSearchMethodPlaceholder(): string {
    switch (this.selectedSearchMethod) {
      case 'ticketNumber':
        return 'Enter ticket number';
      case 'driverLicense':
        return 'Enter driver\'s license number';
      case 'licensePlate':
        return 'Enter license plate number';
      default:
        return '';
    }
  }

  onSubmit(): void {
    if (this.searchForm.valid && this.selectedSearchMethod) {
      this.loading = true;
      this.error = null;
      this.tickets = [];
      this.hasSearched = true;

      const searchValue = this.searchForm.get(this.selectedSearchMethod)?.value;

      if (this.selectedSearchMethod === 'ticketNumber') {
        this.ticketService.getTicket(searchValue).subscribe({
          next: (ticket: Ticket) => {
            this.tickets = [ticket];
            this.loading = false;
          },
          error: (error: Error) => {
            this.error = 'Error searching for ticket';
            this.loading = false;
          }
        });
      } else {
        this.ticketService.searchTickets({
          licensePlate: this.selectedSearchMethod === 'licensePlate' ? searchValue : '',
          driverLicense: this.selectedSearchMethod === 'driverLicense' ? searchValue : ''
        }).subscribe({
          next: (tickets: Ticket[]) => {
            this.tickets = tickets;
            this.loading = false;
          },
          error: (error: Error) => {
            this.error = 'Error searching for tickets';
            this.loading = false;
          }
        });
      }
    }
  }

  selectTicket(ticket: Ticket): void {
    this.selectedTicket = ticket;
  }

  closeModal(): void {
    this.selectedTicket = null;
    this.paymentSuccess = false;
  }

  payTicket(ticket: Ticket): void {
    this.ticketService.payTicket(ticket.ticketNumber).subscribe({
      next: () => {
        ticket.paid = true;
        this.paymentSuccess = true;
      },
      error: (error: Error) => {
        this.error = 'Error processing payment';
      }
    });
  }
} 