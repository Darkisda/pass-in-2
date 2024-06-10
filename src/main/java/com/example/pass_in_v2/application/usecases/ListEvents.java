package com.example.pass_in_v2.application.usecases;

import java.util.List;

import com.example.pass_in_v2.application.adapters.outputs.EventDetailOutput;
import com.example.pass_in_v2.domain.event.EventRepository;

public class ListEvents {
  private final EventRepository repository;

  public ListEvents(EventRepository repository) {
    this.repository = repository;
  }

  public List<EventDetailOutput> exec() {
    var events = this.repository.findAll().stream().map(event -> new EventDetailOutput(event.getId(), event.getTitle(),
        event.getDetails(), event.getSlug(), event.getMaximumAttendees())).toList();
    return events;
  }
}
