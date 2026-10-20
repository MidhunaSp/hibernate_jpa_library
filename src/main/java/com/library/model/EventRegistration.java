package com.library.model;

import com.library.enums.AttendanceStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;

@Entity
@Table(name = "event_registration")
public class EventRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private LibraryEvent event;

    @CreationTimestamp
    private Instant registeredAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private AttendanceStatus attendanceStatus;

    public EventRegistration() {
    }

    public EventRegistration(Member member, LibraryEvent event, AttendanceStatus attendanceStatus) {
        this.member = member;
        this.event = event;
        this.attendanceStatus = attendanceStatus;
    }

    public EventRegistration(AttendanceStatus attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LibraryEvent getEvent() {
        return event;
    }

    public void setEvent(LibraryEvent event) {
        this.event = event;
    }

    public Instant getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Instant registeredAt) {
        this.registeredAt = registeredAt;
    }

    public AttendanceStatus getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(AttendanceStatus attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    @Override
    public String toString() {
        return "EventRegistration{id=" + id + ", registeredAt=" + registeredAt
                + ", attendanceStatus=" + attendanceStatus + '}';
    }
}