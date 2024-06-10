package com.example.pass_in_v2.domain.attendee;

import java.util.List;
import java.util.Optional;

public interface AttendeeRepository {
  Optional<Attendee> findById(String attendeeId);

  Attendee save(Attendee attendee);

  List<AttendeeCheckInAggregate> findByEventId(String eventId);

  Integer countAttendeeFromEventId(String eventId);

  Optional<Attendee> findByEventIdAndEmail(String eventId, String email);
}
