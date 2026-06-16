package com.brightcare.booking.service;

import com.brightcare.booking.dto.ServiceOfferingResponse;
import com.brightcare.booking.repository.ServiceOfferingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceOfferingService {

    private final ServiceOfferingRepository serviceOfferingRepository;

    public ServiceOfferingService(ServiceOfferingRepository serviceOfferingRepository) {
        this.serviceOfferingRepository = serviceOfferingRepository;
    }

    public List<ServiceOfferingResponse> findAll() {
        return serviceOfferingRepository.findAll()
                .stream()
                .map(ServiceOfferingResponse::from)
                .toList();
    }
}

