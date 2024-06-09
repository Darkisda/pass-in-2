package com.example.pass_in_v2.domain.attendee.exceptions;

public class AttendeeAlreadyExistException extends RuntimeException {
  public AttendeeAlreadyExistException(String message) {
    super(message);
  }
}
