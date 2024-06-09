package com.example.pass_in_v2.infra.database.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.pass_in_v2.domain.attendee.Attendee;
import com.example.pass_in_v2.domain.attendee.AttendeeCheckInAggregate;
import com.example.pass_in_v2.domain.attendee.AttendeeRepository;

public interface AttendeeJPARepository extends JpaRepository<Attendee, String>, AttendeeRepository {
  @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM attendees a WHERE a.event_id = ?1")
  Integer countAttendeeFromEventId(String eventId);

  @Query(value = "SELECT a.id, a.name, a.email, a.created_at, ch.created_at as checked_in_at FROM attendees a LEFT JOIN checkins ch ON ch.attendee_id = a.id WHERE a.event_id = ?1",
   nativeQuery = true)
  List<AttendeeCheckInAggregate> findByEventId( @Param("eventId") String eventId);
}
