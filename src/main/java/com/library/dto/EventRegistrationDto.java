package com.library.dto;

import com.library.enums.AttendanceStatus;

import java.time.Instant;

public record EventRegistrationDto(
        Long registrationId,
        Long memberId,
        String memberName,
        Long eventId,
        String eventTitle,
        Instant registeredAt,
        AttendanceStatus attendanceStatus
) {
}
