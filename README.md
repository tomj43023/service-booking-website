# Service Booking Website for a Small Business

BrightCare Cleaning is a full-stack booking website for a small cleaning service. Customers can browse services, submit an appointment request, and send a contact message. A demo admin page lets the business view appointments, update booking status, and delete requests.

## Portfolio Highlights

- Vue 3 single-page application with Vue Router.
- Pinia store for service and appointment state.
- Responsive commercial website design for desktop and mobile.
- Booking and contact forms with frontend validation.
- Java Spring Boot REST API with Jakarta Validation.
- H2 database with seeded demo services and appointments.
- Admin workflow for Pending, Confirmed, and Cancelled appointments.

## Tech Stack

Frontend:

- Vue 3
- Vite
- Pinia
- Vue Router
- Fetch API

Backend:

- Java 21
- Spring Boot 3.3
- Spring Web
- Spring Data JPA
- H2 Database
- Jakarta Validation

## Project Structure

```text
.
├── backend/
│   ├── src/main/java/com/brightcare/booking/
│   │   ├── config/
│   │   ├── controller/
│   │   ├── dto/
│   │   ├── model/
│   │   ├── repository/
│   │   └── service/
│   └── src/main/resources/application.properties
├── frontend/
│   └── src/
│       ├── assets/
│       ├── components/
│       ├── router/
│       ├── services/
│       ├── stores/
│       └── views/
└── docs/superpowers/specs/
```

## Run the Backend

The project includes a Maven Wrapper for Windows. It is configured to keep Maven downloads and dependency cache inside the `backend/.mvn/` folder instead of using the default user home cache.

From the project root:

```powershell
cd backend
powershell -ExecutionPolicy Bypass -Command "Invoke-WebRequest -Uri https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.3.4/maven-wrapper-3.3.4.jar -OutFile .mvn/wrapper/maven-wrapper.jar"
.\mvnw.cmd spring-boot:run
```

Backend URL:

```text
http://localhost:8080
```

H2 Console:

```text
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:file:./data/bookingdb
User: sa
Password: leave blank
```

## Run the Frontend

From a second terminal:

```powershell
cd frontend
npm install --cache .npm-cache
npm run dev
```

Frontend URL:

```text
http://localhost:5173
```

If your backend runs somewhere else, create `frontend/.env`:

```text
VITE_API_BASE_URL=http://localhost:8080/api
```

## API Summary

```text
GET    /api/services
GET    /api/appointments
POST   /api/appointments
PATCH  /api/appointments/{id}/status
DELETE /api/appointments/{id}
POST   /api/contact
```

Appointment status values:

```text
PENDING
CONFIRMED
CANCELLED
```

## Demo Data

The backend seeds:

- 4 service offerings.
- 5 appointment records.

The seed only runs when the corresponding database tables are empty, so newly created appointments can remain available while using the H2 file database.

## Pages

- Home: business introduction, service highlights, booking call to action.
- Services: service cards with price, duration, description, and Book links.
- Booking: customer appointment form with validation and success summary.
- Admin: appointment list, status update controls, and delete action.
- Contact: validated contact form.

## Notes

The Admin page is intentionally open for portfolio/demo purposes. A production version should add authentication, authorization, audit history, and stricter operational controls.

