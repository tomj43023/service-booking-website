package com.brightcare.booking.config;

import com.brightcare.booking.model.Appointment;
import com.brightcare.booking.model.AppointmentStatus;
import com.brightcare.booking.model.ServiceOffering;
import com.brightcare.booking.repository.AppointmentRepository;
import com.brightcare.booking.repository.ServiceOfferingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(ServiceOfferingRepository services, AppointmentRepository appointments) {
        return args -> {
            if (services.count() == 0) {
                services.saveAll(List.of(
                        new ServiceOffering(
                                "Standard Home Cleaning",
                                "A recurring-friendly reset for kitchens, bathrooms, bedrooms, and high-touch surfaces.",
                                new BigDecimal("120.00"),
                                120,
                                "Residential",
                                "Best for weekly and bi-weekly upkeep"
                        ),
                        new ServiceOffering(
                                "Deep Cleaning",
                                "Detailed scrubbing for baseboards, fixtures, cabinet fronts, appliances, and built-up dust.",
                                new BigDecimal("220.00"),
                                210,
                                "Residential",
                                "Top choice for first-time clients"
                        ),
                        new ServiceOffering(
                                "Move-in/out Cleaning",
                                "Empty-home cleaning for apartments, townhomes, and houses before keys change hands.",
                                new BigDecimal("260.00"),
                                240,
                                "Move",
                                "Built for deposits and fresh starts"
                        ),
                        new ServiceOffering(
                                "Office Cleaning",
                                "After-hours cleaning for small offices, studios, and consulting spaces.",
                                new BigDecimal("180.00"),
                                150,
                                "Commercial",
                                "Keeps workspaces ready for the next day"
                        )
                ));
            }

            if (appointments.count() == 0) {
                List<ServiceOffering> savedServices = services.findAll();
                appointments.saveAll(List.of(
                        new Appointment("Maya Chen", "maya.chen@example.com", "(555) 013-2401", savedServices.get(0), LocalDate.now().plusDays(1), LocalTime.of(9, 0), AppointmentStatus.PENDING, "Please focus on the kitchen counters."),
                        new Appointment("Jordan Lee", "jordan.lee@example.com", "(555) 013-8712", savedServices.get(1), LocalDate.now().plusDays(2), LocalTime.of(13, 30), AppointmentStatus.CONFIRMED, "Two-bedroom apartment, first visit."),
                        new Appointment("Avery Smith", "avery.smith@example.com", "(555) 013-0918", savedServices.get(2), LocalDate.now().plusDays(3), LocalTime.of(10, 0), AppointmentStatus.PENDING, "Move-out inspection is the next morning."),
                        new Appointment("Priya Patel", "priya.patel@example.com", "(555) 013-5580", savedServices.get(3), LocalDate.now().plusDays(4), LocalTime.of(18, 0), AppointmentStatus.CONFIRMED, "Office has parking behind the building."),
                        new Appointment("Noah Brown", "noah.brown@example.com", "(555) 013-3377", savedServices.get(0), LocalDate.now().plusDays(5), LocalTime.of(11, 30), AppointmentStatus.CANCELLED, "Client requested reschedule later.")
                ));
            }
        };
    }
}

