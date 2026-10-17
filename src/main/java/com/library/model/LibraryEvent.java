package com.library.model;

import com.library.enums.EventType;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class LibraryEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Instant eventDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EventType eventType;

    public LibraryEvent() {
    }

    public LibraryEvent(String title, Instant eventDate, EventType eventType) {
        this.title = title;
        this.eventDate = eventDate;
        this.eventType = eventType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instant getEventDate() {
        return eventDate;
    }

    public void setEventDate(Instant eventDate) {
        this.eventDate = eventDate;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "LibraryEvent{id=" + id + ", title='" + title + "', eventDate=" + eventDate
                + ", eventType=" + eventType + '}';
    }
}