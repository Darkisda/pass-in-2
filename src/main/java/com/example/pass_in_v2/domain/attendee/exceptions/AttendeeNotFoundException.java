package com.example.pass_in_v2.domain.attendee.exceptions;

public class AttendeeNotFoundException extends RuntimeException {
  public AttendeeNotFoundException(String message) {
    super(message);
  }
}
