import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Ticket {
  ticketNumber: string;
  ticketType: 'PARKING' | 'SPEEDING';
  licensePlateNumber: string;
  fineAmount: number;
  dueDate: string;
  paid: boolean;
  vehicle?: {
    ownerFirstName: string;
    ownerLastName: string;
    driverLicenseNumber: string;
  };
}

export interface TicketSearchRequest {
  licensePlate?: string;
  driverLicense?: string;
}

@Injectable({
  providedIn: 'root'
})
export class TicketService {
  private apiUrl = 'http://localhost:8080/api/tickets';

  constructor(private http: HttpClient) { }

  searchTickets(request: TicketSearchRequest): Observable<Ticket[]> {
    return this.http.post<Ticket[]>(`${this.apiUrl}/search`, request);
  }

  getTicket(ticketNumber: string): Observable<Ticket> {
    return this.http.get<Ticket>(`${this.apiUrl}/${ticketNumber}`);
  }

  payTicket(ticketNumber: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/${ticketNumber}/pay`, {});
  }
} 