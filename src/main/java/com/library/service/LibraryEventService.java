package com.library.service;

import com.library.enums.EventType;
import com.library.exception.ResourceNotFoundException;
import com.library.model.LibraryEvent;
import com.library.repository.LibraryEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryEventService {

    private final LibraryEventRepository eventRepository;

    public LibraryEventService(LibraryEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Transactional
    public void insertEvent(String title, Instant eventDate, EventType eventType) {
        LibraryEvent event = new LibraryEvent(title, eventDate, eventType);
        eventRepository.insertEvent(event);
    }

    public LibraryEvent findById(Long id) {
        Optional<LibraryEvent> optional = eventRepository.findById(id);
        if (optional.isEmpty())
            throw new ResourceNotFoundException("event id invalid: " + id);
        return optional.get();
    }

    public List<LibraryEvent> findAll() {
        return eventRepository.findAll();
    }
}