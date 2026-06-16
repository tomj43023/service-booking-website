package com.brightcare.booking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ContactMessageRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 80, message = "Name must be 80 characters or fewer")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Enter a valid email address")
        String email,

        @NotBlank(message = "Subject is required")
        @Size(max = 120, message = "Subject must be 120 characters or fewer")
        String subject,

        @NotBlank(message = "Message is required")
        @Size(min = 10, max = 1200, message = "Message must be between 10 and 1200 characters")
        String message
) {
}

