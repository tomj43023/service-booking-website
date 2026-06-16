package com.brightcare.booking.service;

import com.brightcare.booking.dto.AppointmentCreateRequest;
import com.brightcare.booking.dto.AppointmentResponse;
import com.brightcare.booking.model.Appointment;
import com.brightcare.booking.model.AppointmentStatus;
import com.brightcare.booking.model.ServiceOffering;
import com.brightcare.booking.repository.AppointmentRepository;
import com.brightcare.booking.repository.ServiceOfferingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ServiceOfferingRepository serviceOfferingRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, ServiceOfferingRepository serviceOfferingRepository) {
        this.appointmentRepository = appointmentRepository;
        this.serviceOfferingRepository = serviceOfferingRepository;
    }

    public List<AppointmentResponse> findAll() {
        return appointmentRepository.findAllByOrderByAppointmentDateAscAppointmentTimeAsc()
                .stream()
                .map(AppointmentResponse::from)
                .toList();
    }

    @Transactional
    public AppointmentResponse create(AppointmentCreateRequest request) {
        ServiceOffering service = serviceOfferingRepository.findById(request.serviceId())
                .orElseThrow(() -> new ResourceNotFoundException("Service was not found"));

        Appointment appointment = new Appointment(
                request.customerName(),
                request.email(),
                request.phone(),
                service,
                request.appointmentDate(),
                request.appointmentTime(),
                AppointmentStatus.PENDING,
                request.notes()
        );

        return AppointmentResponse.from(appointmentRepository.save(appointment));
    }

    @Transactional
    public AppointmentResponse updateStatus(Long id, AppointmentStatus status) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment was not found"));
        appointment.setStatus(status);
        return AppointmentResponse.from(appointment);
    }

    @Transactional
    public void delete(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Appointment was not found");
        }
        appointmentRepository.deleteById(id);
    }
}

