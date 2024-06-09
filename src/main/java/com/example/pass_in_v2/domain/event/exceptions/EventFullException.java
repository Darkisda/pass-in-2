package com.example.pass_in_v2.domain.event.exceptions;

public class EventFullException extends RuntimeException {
  public EventFullException(String message) {
    super(message);
  }
}
