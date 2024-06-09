package com.example.pass_in_v2.application.usecases;

import java.util.List;

import com.example.pass_in_v2.domain.attendee.AttendeeCheckInAggregate;
import com.example.pass_in_v2.domain.attendee.AttendeeRepository;

public class AttendeesFromEvent {
  private final AttendeeRepository repository;

  public AttendeesFromEvent(AttendeeRepository repository) {
    this.repository = repository;
  }

  public List<AttendeeCheckInAggregate> exec(String eventId) {
   return this.repository.findByEventId(eventId); 
  }
}
