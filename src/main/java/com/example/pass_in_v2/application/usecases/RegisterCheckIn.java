package com.example.pass_in_v2.application.usecases;

import java.time.LocalDateTime;

import com.example.pass_in_v2.domain.attendee.AttendeeRepository;
import com.example.pass_in_v2.domain.attendee.exceptions.AttendeeAlreadyExistException;
import com.example.pass_in_v2.domain.attendee.exceptions.AttendeeNotFoundException;
import com.example.pass_in_v2.domain.checkin.CheckIn;
import com.example.pass_in_v2.domain.checkin.CheckInRepository;

public class RegisterCheckIn {
  private final CheckInRepository repository;
  private final AttendeeRepository attendeeRepository;

  public RegisterCheckIn(CheckInRepository repository, AttendeeRepository attendeeRepository) {
    this.repository = repository;
    this.attendeeRepository = attendeeRepository;
  }

  public void exec(String attendeeId) {
    this.verifyCheckInExists(attendeeId);
    var attendee = this.attendeeRepository.findById(attendeeId).orElseThrow(() -> new AttendeeNotFoundException("Attendee not found"));
    var newCheckIn = new CheckIn();
    newCheckIn.setAttendee(attendee);
    newCheckIn.setCreatedAt(LocalDateTime.now());
    this.repository.save(newCheckIn);
  }

  private void verifyCheckInExists(String attendeeId) {
    var checkIn = this.repository.findByAttendeeId(attendeeId);
    if (checkIn.isPresent())
      throw new AttendeeAlreadyExistException("Attendee already checked in");
  }
}
