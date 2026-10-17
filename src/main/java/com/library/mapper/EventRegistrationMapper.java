package com.library.mapper;

import com.library.dto.EventRegistrationDto;
import com.library.dto.EventRegistrationDto;
import com.library.model.EventRegistration;

public class EventRegistrationMapper {

    public static EventRegistrationDto mapToDto(EventRegistration registration) {
        return new EventRegistrationDto(
                registration.getId(),
                registration.getMember().getId(),
                registration.getMember().getName(),
                registration.getEvent().getId(),
                registration.getEvent().getTitle(),
                registration.getRegisteredAt(),
                registration.getAttendanceStatus()
        );
    }
}