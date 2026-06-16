# Service Booking Website Design

Date: 2026-06-16

## Goal

Build a portfolio-ready service booking website for a small cleaning business. The project should demonstrate Vue 3, responsive commercial website design, form validation, appointment booking, admin appointment management, and a real Java Spring Boot backend with H2 persistence.

## Chosen Direction

- Business type: cleaning service.
- Visual style: fresh, trustworthy local-service brand.
- Architecture: frontend and backend separated in one repository.
- Frontend stack: Vue 3, Vite, Pinia, Vue Router.
- Backend stack: Java Spring Boot, H2, Spring Data JPA, Jakarta Validation.

## Repository Structure

```text
frontend/
  src/
    assets/
    components/
    router/
    services/
    stores/
    views/
backend/
  src/main/java/...
    config/
    controller/
    dto/
    model/
    repository/
    service/
  src/main/resources/
docs/superpowers/specs/
```

## User Experience

The website will feel like a real small business site for a cleaning company. The homepage will introduce the company, highlight benefits, and make the booking call to action obvious. The services page will show clear prices, durations, descriptions, and direct booking links. The booking page will guide customers through selecting a service, date, and time, then show a success state after submission.

The admin page is a demo admin dashboard, not a secured production back office. It will show seeded and submitted appointments, let the user change appointment status, and delete appointments. The README will call out that authentication is intentionally out of scope for this portfolio version.

## Pages

### Home

- Brand introduction for a cleaning service.
- Service highlights and trust indicators.
- Primary call to action: Book Now.
- Responsive first screen with booking access visible on mobile.

### Services

- Display four cleaning services:
  - Standard Home Cleaning.
  - Deep Cleaning.
  - Move-in/out Cleaning.
  - Office Cleaning.
- Each service includes price, duration, description, and a Book button.
- Book buttons route to Booking with the selected service id.

### Booking

- Fields: name, email, phone, service, date, time, optional notes.
- Frontend validation for required fields, email format, phone length, service, date, and time.
- Backend validation using Jakarta Validation.
- Successful submission creates a Pending appointment and displays an appointment summary.

### Admin

- Appointment list with at least five demo records.
- Status values: Pending, Confirmed, Cancelled.
- Status updates through API.
- Delete appointment action with browser confirmation.
- Desktop layout uses a clear table or dense list; mobile layout becomes readable cards.

### Contact

- Fields: name, email, subject, message.
- Frontend and backend validation.
- Success feedback after submission.

## Data Model

### ServiceOffering

- id
- name
- description
- price
- durationMinutes
- category
- highlight

### Appointment

- id
- customerName
- email
- phone
- service relation or service id
- serviceName snapshot
- appointmentDate
- appointmentTime
- status
- notes
- createdAt

### ContactMessage

- id
- name
- email
- subject
- message
- createdAt

## API

```text
GET    /api/services
GET    /api/appointments
POST   /api/appointments
PATCH  /api/appointments/{id}/status
DELETE /api/appointments/{id}
POST   /api/contact
```

Validation errors should return structured responses that the frontend can show near the relevant form or in a page alert.

## Backend Behavior

- Enable CORS for the Vite dev server.
- Enable H2 Console in development.
- Seed service offerings and at least five appointments at startup.
- Default new appointments to Pending.
- Preserve service name on appointments so admin records stay readable even if services later change.
- Reject invalid status updates.

## Frontend Behavior

- Use Vue Router for Home, Services, Booking, Admin, and Contact.
- Use a service layer for API calls.
- Use Pinia for appointment and service state where it improves clarity.
- Show loading, success, and error states for async operations.
- Use reusable components such as AppNavbar, AppFooter, ServiceCard, BookingForm, StatusBadge, and AppointmentList.

## Responsive Design

- Navigation compresses cleanly on mobile.
- Service cards collapse from multi-column desktop layouts to one column on mobile.
- Booking form uses a two-column layout on larger screens and a single-column layout on mobile.
- Admin appointment rows become stacked cards on mobile.
- Text and controls must not overlap or overflow their containers.

## Verification

- Run frontend dependency install and build.
- Run backend tests or Maven verification.
- If feasible, start both apps and manually check that:
  - services load from the backend,
  - booking creates an appointment,
  - admin can update status,
  - admin can delete an appointment,
  - contact form submits,
  - core pages are usable on mobile width.

## README Requirements

The README will explain:

- business scenario,
- feature list,
- technology stack,
- directory structure,
- backend run command,
- frontend run command,
- API summary,
- H2 Console access,
- demo data,
- portfolio highlights.

## Out of Scope

- Admin authentication.
- Online payment.
- Email or SMS notifications.
- Calendar integrations.
- Production database deployment.
