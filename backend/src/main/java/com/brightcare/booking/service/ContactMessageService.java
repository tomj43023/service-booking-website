package com.brightcare.booking.service;

import com.brightcare.booking.dto.ContactMessageRequest;
import com.brightcare.booking.dto.ContactMessageResponse;
import com.brightcare.booking.model.ContactMessage;
import com.brightcare.booking.repository.ContactMessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;

    public ContactMessageService(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    @Transactional
    public ContactMessageResponse create(ContactMessageRequest request) {
        ContactMessage message = new ContactMessage(
                request.name(),
                request.email(),
                request.subject(),
                request.message()
        );
        return ContactMessageResponse.from(contactMessageRepository.save(message));
    }
}

