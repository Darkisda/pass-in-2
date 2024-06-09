package com.example.pass_in_v2.application.usecases;

import java.time.LocalDateTime;

import com.example.pass_in_v2.application.adapters.inputs.CreateAttendeeInput;
import com.example.pass_in_v2.application.adapters.outputs.AttendeeIdOutput;
import com.example.pass_in_v2.domain.attendee.Attendee;
import com.example.pass_in_v2.domain.attendee.AttendeeRepository;

public class CreateAttendee {
  private final AttendeeRepository repository;

  public CreateAttendee(AttendeeRepository repository) {
    this.repository = repository;
  }

  public AttendeeIdOutput exec(CreateAttendeeInput input) {
    var newAttendee = new Attendee();
    newAttendee.setName(input.name());
    newAttendee.setEmail(input.email());
    newAttendee.setCreatedAt(LocalDateTime.now());
    var attendee = this.repository.save(newAttendee);
    return new AttendeeIdOutput(attendee.getId());
  }
}
