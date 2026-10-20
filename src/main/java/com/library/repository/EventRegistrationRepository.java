package com.library.repository;

import com.library.enums.AttendanceStatus;
import com.library.model.EventRegistration;
import com.library.model.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EventRegistrationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void insertRegistration(EventRegistration registration) {
        entityManager.persist(registration);
    }

    public Optional<EventRegistration> findById(Long id) {
        return Optional.ofNullable(entityManager.find(EventRegistration.class, id));
    }

    // task 6: all registrations for a given event
    public List<EventRegistration> findRegistrationsForEvent(Long eventId) {
        String jpql = "select r from EventRegistration r where r.event.id = ?1";

        return entityManager.createQuery(jpql, EventRegistration.class)
                .setParameter(1, eventId)
                .getResultList();
    }

    // task 7: members who registered for the event but did not attend
    // filtering directly on EventRegistration.attendanceStatus
    public List<Member> findNoShowsForEvent(Long eventId) {
        String jpql = """
                select r.member
                from EventRegistration r
                where r.event.id = ?1
                and r.attendanceStatus = ?2
                """;

        return entityManager.createQuery(jpql, Member.class)
                .setParameter(1, eventId)
                .setParameter(2, AttendanceStatus.NO_SHOW)
                .getResultList();
    }
}