package com.example.pass_in_v2.application.usecases;

import com.example.pass_in_v2.application.adapters.outputs.EventDetailOutput;
import com.example.pass_in_v2.domain.event.EventRepository;
import com.example.pass_in_v2.domain.event.exceptions.EventNotFoundException;

public class FindEventById {
  private final EventRepository repository;

  public FindEventById(EventRepository repository) {
    this.repository = repository;
  }

  public EventDetailOutput exec(String eventId) {
    var event = this.repository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not found"));
    return new EventDetailOutput(event.getId(), event.getTitle(), event.getDetails(), event.getSlug(),event.getMaximumAttendees());
  }
}
