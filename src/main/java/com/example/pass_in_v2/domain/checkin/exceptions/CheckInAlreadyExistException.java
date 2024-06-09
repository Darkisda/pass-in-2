package com.example.pass_in_v2.domain.checkin.exceptions;

public class CheckInAlreadyExistException extends RuntimeException {
  public CheckInAlreadyExistException(String message) {
    super(message);
  }
}
