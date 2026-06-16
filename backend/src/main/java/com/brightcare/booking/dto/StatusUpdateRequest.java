package com.brightcare.booking.dto;

import com.brightcare.booking.model.AppointmentStatus;
import jakarta.validation.constraints.NotNull;

public record StatusUpdateRequest(
        @NotNull(message = "Status is required")
        AppointmentStatus status
) {
}

