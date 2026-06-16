package com.brightcare.booking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentCreateRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 80, message = "Name must be 80 characters or fewer")
        String customerName,

        @NotBlank(message = "Email is required")
        @Email(message = "Enter a valid email address")
        String email,

        @NotBlank(message = "Phone is required")
        @Pattern(regexp = "^[0-9+()\\-\\s]{7,24}$", message = "Enter a valid phone number")
        String phone,

        @NotNull(message = "Service is required")
        Long serviceId,

        @NotNull(message = "Date is required")
        @FutureOrPresent(message = "Date cannot be in the past")
        LocalDate appointmentDate,

        @NotNull(message = "Time is required")
        LocalTime appointmentTime,

        @Size(max = 600, message = "Notes must be 600 characters or fewer")
        String notes
) {
}

