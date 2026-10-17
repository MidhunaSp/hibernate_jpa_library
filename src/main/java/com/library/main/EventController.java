package com.library.main;

import com.library.config.AppConfig;
import com.library.dto.EventRegistrationDto;
import com.library.enums.AttendanceStatus;
import com.library.enums.EventType;
import com.library.enums.MembershipType;
import com.library.model.Member;
import com.library.service.EventRegistrationService;
import com.library.service.LibraryEventService;
import com.library.service.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class EventController {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = context.getBean(MemberService.class);
        LibraryEventService eventService = context.getBean(LibraryEventService.class);
        EventRegistrationService registrationService = context.getBean(EventRegistrationService.class);

        // members 1 and 2 (Mahi, Luna) are already added by BookController, adding a third one
        memberService.insertMember("Kavin", "kavin@gmail.com", MembershipType.STANDARD);

        // add an event
        eventService.insertEvent("Dan Brown Author Talk",
                Instant.now().plus(7, ChronoUnit.DAYS), EventType.AUTHOR_TALK);

        // register members for event 1
        registrationService.registerMember(1L, 1L);
        registrationService.registerMember(2L, 1L);
        registrationService.registerMember(3L, 1L);

        // registration 1 attended, registration 2 no show, registration 3 stays REGISTERED
        registrationService.updateAttendance(1L, AttendanceStatus.ATTENDED);
        registrationService.updateAttendance(2L, AttendanceStatus.NO_SHOW);

        // task 6 + task 8
        System.out.println("---- Registrations for event 1 ----");
        List<EventRegistrationDto> registrations = registrationService.findRegistrationsForEvent(1L);
        registrations.forEach(System.out::println);

        // task 7
        System.out.println("---- No shows for event 1 ----");
        List<Member> noShows = registrationService.findNoShowsForEvent(1L);
        noShows.forEach(System.out::println);

        // task 1: Member findAll
        System.out.println("---- All members ----");
        memberService.findAll().forEach(System.out::println);
    }
}