package com.example.pass_in_v2.application.usecases;

import com.example.pass_in_v2.application.adapters.outputs.AttendeeDetailOutput;
import com.example.pass_in_v2.domain.attendee.AttendeeRepository;
import com.example.pass_in_v2.domain.attendee.exceptions.AttendeeNotFoundException;

public class FindAttendeeById {
  private final AttendeeRepository repository;

  public FindAttendeeById(AttendeeRepository repository) {
    this.repository = repository;
  }

  public AttendeeDetailOutput exec(String attendeeId) {
    var response = this.repository.findById(attendeeId)
        .orElseThrow(() -> new AttendeeNotFoundException("Attendee not found"));
    return new AttendeeDetailOutput(response.getId(), response.getName(), response.getEmail(), response.getCreatedAt());
  }
}
