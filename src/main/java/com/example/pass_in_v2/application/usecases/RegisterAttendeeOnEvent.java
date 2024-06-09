package com.example.pass_in_v2.application.usecases;

import java.time.LocalDateTime;

import com.example.pass_in_v2.application.adapters.inputs.CreateAttendeeInput;
import com.example.pass_in_v2.application.adapters.outputs.AttendeeIdOutput;
import com.example.pass_in_v2.domain.attendee.Attendee;
import com.example.pass_in_v2.domain.attendee.AttendeeRepository;
import com.example.pass_in_v2.domain.attendee.exceptions.AttendeeAlreadyExistException;
import com.example.pass_in_v2.domain.event.EventRepository;
import com.example.pass_in_v2.domain.event.exceptions.EventFullException;
import com.example.pass_in_v2.domain.event.exceptions.EventNotFoundException;

public class RegisterAttendeeOnEvent {
  private final AttendeeRepository repository;
  private final EventRepository eventRepository;

  public RegisterAttendeeOnEvent(AttendeeRepository repository, EventRepository eventRepository) {
    this.repository = repository;
    this.eventRepository = eventRepository;
  }

  public AttendeeIdOutput exec(CreateAttendeeInput input, String eventId) {
    this.verifyAttendeeSubscription(eventId, input.email());
    var event = this.eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not found"));
    var totalOnEvent = this.repository.countAttendeeFromEventId(eventId);
    if (event.getMaximumAttendees() <= totalOnEvent) {
      throw new EventFullException("Event is full");
    }
    var newAttendee = new Attendee();
    newAttendee.setName(input.name());
    newAttendee.setEmail(input.email());
    newAttendee.setCreatedAt(LocalDateTime.now());
    newAttendee.setEvent(event);
    var attendee = this.repository.save(newAttendee);
    return new AttendeeIdOutput(attendee.getId());
  }

  private void verifyAttendeeSubscription(String eventId, String email) {
    var isAttendeeAlreadyRegistered = this.repository.findByEventIdAndEmail(eventId, email);
    if (isAttendeeAlreadyRegistered.isPresent()) {
      throw new AttendeeAlreadyExistException("Attendee already registered");
    }
  }
}
