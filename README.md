# Event Management System

## Overview
The **Event Management System** is a comprehensive platform designed to facilitate event organization, seat reservations, and user authentication. It features a secure authentication mechanism, event lifecycle management, seat booking with QR code validation, and an admin dashboard for efficient control.

## Core Modules

### 1. Authentication Module
**Purpose:** Handles user and admin authentication, including login, registration, OTP verification, and JWT-based authentication.
#### Components:
- User registration and login APIs.
- OTP-based email verification for secure registration.
- JWT authentication with role-based access control.

### 2. Event Management Module
**Purpose:** Allows admins to create, update, open, and close events efficiently.
#### Components:
- CRUD APIs for event management.
- Admin controls for managing the event lifecycle.
- Integration with Google Calendar for scheduling.

### 3. Booking and Seat Reservation Module
**Purpose:** Manages seat selection, reservations, and user entry verification.
#### Components:
- APIs for seat availability and booking.
- QR code generation for event entry validation.
- Admin verification for user bookings.

### 4. History and Reporting Module
**Purpose:** Enables users to track their past event participation and generate reports.
#### Components:
- APIs for retrieving past event history.
- Reporting and analytics features.

### 5. User Profile and Preferences Module
**Purpose:** Allows users to manage their personal details and notification settings.
#### Components:
- APIs for updating user details.
- Customizable notification preferences.

### 6. Admin Dashboard Module
**Purpose:** Provides a centralized interface for managing events and bookings.
#### Components:
- Event and booking management.
- User activity tracking.
- Administrative controls.

## Technologies Used
- **Backend:** Spring Boot, Spring Security,  JPA, ORM, RESTful APIs
- **Database:** MySQL
- **Authentication:** JWT, OTP-based email verification
- **Frontend:** Not Created yet 
- **Other Integrations:** QR Code Generation

## Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/vinayakkushwah01/event-management-system.git
   ```
2. Navigate to the project directory:
   ```bash
   cd event-management-system
   ```
3. Configure database settings in `application.properties`.
4. Build and run the backend:
   ```bash
   mvn spring-boot:run
   ```
5. Access APIs via Postman or frontend integration.

## Contributing
Feel free to fork this repository and submit pull requests.

## License
This project is licensed under the [MIT License](LICENSE).

