# Traffic Ticket Management System

## Project Overview
The Traffic Ticket Management System is a web-based application that allows users to search for and pay their traffic tickets online. Users can look up their tickets using either their vehicle's license plate number or their driver's license number. The system provides a user-friendly interface to view ticket details and process payments, making the ticket management process more efficient and convenient for both users and administrators.

## Technology Stack
- **Frontend**: Angular
  - Modern, component-based architecture
  - Responsive design for optimal user experience
  - Material Design components for consistent UI

- **Backend**: Java with Spring Boot
  - RESTful API architecture
  - Spring Security for authentication and authorization
  - Spring Data JPA for database operations

- **Database**: SQLite
  - Lightweight, serverless database
  - Easy to set up and maintain
  - Perfect for development and small to medium-scale applications

## Core Features

### 1. Ticket Search
- Search by license plate number
- Search by driver's license number
- Display of all matching tickets
- Real-time search results

### 2. Ticket Details
- Popup modal for detailed ticket information
- Display of:
  - Ticket number
  - Ticket type (parking/speeding)
  - Fine amount
  - Due date
  - Vehicle information
  - Driver information

### 3. Payment Processing
- Secure payment interface
- Receipt generation
- Confirmation page with thank you message

### 4. Database Structure
#### Tickets Table
- Ticket number (10-digit unique identifier)
- Ticket type (parking/speeding)
- License plate number
- Fine amount
- Due date

#### Vehicles Table
- License plate number
- Driver's license number
- Last name
- First name

### 5. User Interface
- Clean, intuitive design
- Responsive layout for all devices
- Easy navigation between pages
- Clear call-to-action buttons
- Modal popups for detailed information 