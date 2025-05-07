export interface Vehicle {
  ownerFirstName: string;
  ownerLastName: string;
  driverLicenseNumber: string;
}

export interface Ticket {
  ticketNumber: string;
  ticketType: string;
  fineAmount: number;
  dueDate: string;
  paid: boolean;
  licensePlateNumber: string;
  vehicle?: Vehicle;
} 