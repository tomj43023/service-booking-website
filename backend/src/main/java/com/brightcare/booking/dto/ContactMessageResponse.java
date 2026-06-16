package com.brightcare.booking.dto;

import com.brightcare.booking.model.ContactMessage;

import java.time.LocalDateTime;

public record ContactMessageResponse(
        Long id,
        String name,
        String email,
        String subject,
        String message,
        LocalDateTime createdAt
) {
    public static ContactMessageResponse from(ContactMessage message) {
        return new ContactMessageResponse(
                message.getId(),
                message.getName(),
                message.getEmail(),
                message.getSubject(),
                message.getMessage(),
                message.getCreatedAt()
        );
    }
}

