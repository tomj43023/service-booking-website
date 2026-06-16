package com.brightcare.booking.dto;

import com.brightcare.booking.model.ServiceOffering;

import java.math.BigDecimal;

public record ServiceOfferingResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer durationMinutes,
        String category,
        String highlight
) {
    public static ServiceOfferingResponse from(ServiceOffering service) {
        return new ServiceOfferingResponse(
                service.getId(),
                service.getName(),
                service.getDescription(),
                service.getPrice(),
                service.getDurationMinutes(),
                service.getCategory(),
                service.getHighlight()
        );
    }
}

