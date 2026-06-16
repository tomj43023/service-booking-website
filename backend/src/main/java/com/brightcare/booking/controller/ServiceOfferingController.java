package com.brightcare.booking.controller;

import com.brightcare.booking.dto.ServiceOfferingResponse;
import com.brightcare.booking.service.ServiceOfferingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    public ServiceOfferingController(ServiceOfferingService serviceOfferingService) {
        this.serviceOfferingService = serviceOfferingService;
    }

    @GetMapping
    public List<ServiceOfferingResponse> findAll() {
        return serviceOfferingService.findAll();
    }
}

