package com.example.pass_in_v2.domain.attendee;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AttendeeCheckInAggregate {
  String getId();
  String getName();
  String getEmail();
  LocalDateTime getCreatedAt();
  Optional<LocalDateTime> getCheckedInAt();
}
