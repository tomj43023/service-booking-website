package com.brightcare.booking.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ApiErrorResponse(
        LocalDateTime timestamp,
        int status,
        String message,
        Map<String, String> fieldErrors
) {
    public static ApiErrorResponse of(int status, String message, Map<String, String> fieldErrors) {
        return new ApiErrorResponse(LocalDateTime.now(), status, message, fieldErrors);
    }
}

