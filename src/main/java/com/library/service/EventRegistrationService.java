package com.library.service;

import com.library.dto.EventRegistrationDto;
import com.library.enums.AttendanceStatus;
import com.library.exception.ResourceNotFoundException;
import com.library.mapper.EventRegistrationMapper;
import com.library.model.EventRegistration;
import com.library.model.LibraryEvent;
import com.library.model.Member;
import com.library.repository.EventRegistrationRepository;
import com.library.repository.LibraryEventRepository;
import com.library.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventRegistrationService {

    private final EventRegistrationRepository registrationRepository;
    private final LibraryEventRepository eventRepository;
    private final MemberRepository memberRepository;

    public EventRegistrationService(EventRegistrationRepository registrationRepository,
                                    LibraryEventRepository eventRepository,
                                    MemberRepository memberRepository) {
        this.registrationRepository = registrationRepository;
        this.eventRepository = eventRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void registerMember(Long memberId, Long eventId) {
        // Step 1: fetch Member obj using given memberId
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isEmpty())
            throw new ResourceNotFoundException("member id invalid");
        Member member = optionalMember.get();

        // Step 2: fetch LibraryEvent obj using given eventId
        Optional<LibraryEvent> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isEmpty())
            throw new ResourceNotFoundException("event id invalid");
        LibraryEvent event = optionalEvent.get();

        // Step 3: prepare EventRegistration object (starts as REGISTERED)
        EventRegistration registration = new EventRegistration(AttendanceStatus.REGISTERED);

        // Step 4: Attach member and event to registration obj
        registration.setMember(member);
        registration.setEvent(event);

        // Step 5: Add registration object
        registrationRepository.insertRegistration(registration);
    }

    @Transactional
    public void updateAttendance(Long registrationId, AttendanceStatus attendanceStatus) {
        Optional<EventRegistration> optional = registrationRepository.findById(registrationId);
        if (optional.isEmpty())
            throw new ResourceNotFoundException("registration id invalid");
        EventRegistration registration = optional.get();
        registration.setAttendanceStatus(attendanceStatus);
    }

    // task 6 + task 8: returns DTOs instead of entities
    public List<EventRegistrationDto> findRegistrationsForEvent(Long eventId) {
        // step 1: fetch all registrations of the event : List<EventRegistration>
        List<EventRegistration> list = registrationRepository.findRegistrationsForEvent(eventId);

        // step 2: convert List<EventRegistration> to List<EventRegistrationDTO> using Mapper
        return list
                .stream()
                .map(EventRegistrationMapper::mapToDto)
                .toList();
    }

    // task 7
    public List<Member> findNoShowsForEvent(Long eventId) {
        return registrationRepository.findNoShowsForEvent(eventId);
    }
}