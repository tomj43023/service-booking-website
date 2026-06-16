package com.brightcare.booking.dto;

import com.brightcare.booking.model.Appointment;
import com.brightcare.booking.model.AppointmentStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record AppointmentResponse(
        Long id,
        String customerName,
        String email,
        String phone,
        Long serviceId,
        String serviceName,
        LocalDate appointmentDate,
        LocalTime appointmentTime,
        AppointmentStatus status,
        String notes,
        LocalDateTime createdAt
) {
    public static AppointmentResponse from(Appointment appointment) {
        return new AppointmentResponse(
                appointment.getId(),
                appointment.getCustomerName(),
                appointment.getEmail(),
                appointment.getPhone(),
                appointment.getService().getId(),
                appointment.getServiceName(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime(),
                appointment.getStatus(),
                appointment.getNotes(),
                appointment.getCreatedAt()
        );
    }
}

