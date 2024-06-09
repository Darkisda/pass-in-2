package com.example.pass_in_v2.application.usecases;

import java.text.Normalizer;
import java.text.Normalizer.Form;

import com.example.pass_in_v2.application.adapters.inputs.CreateEventInput;
import com.example.pass_in_v2.application.adapters.outputs.EventIdOutput;
import com.example.pass_in_v2.domain.event.Event;
import com.example.pass_in_v2.domain.event.EventRepository;

public class CreateEvent {
  private final EventRepository repository;

  public CreateEvent(EventRepository repository) {
    this.repository = repository;
  }

  public EventIdOutput exec(CreateEventInput input) {
    var newEvent = new Event();
    newEvent.setTitle(input.title());
    newEvent.setDetails(input.details());
    newEvent.setSlug(this.createSlug(input.title()));
    newEvent.setMaximumAttendees(input.maximumAttendees());
    var event = this.repository.save(newEvent);
    
    return new EventIdOutput(event.getId());
  }

    private String createSlug(String text) {
    return Normalizer.normalize(text, Form.NFD).replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
        .replaceAll("[^\\w\\s]", "").replaceAll("[\\s+]", "-").toLowerCase();
  }
}
