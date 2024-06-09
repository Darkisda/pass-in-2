package com.example.pass_in_v2.infra.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.pass_in_v2.application.adapters.inputs.CreateAttendeeInput;
import com.example.pass_in_v2.application.adapters.inputs.CreateEventInput;
import com.example.pass_in_v2.application.adapters.outputs.AttendeeIdOutput;
import com.example.pass_in_v2.application.adapters.outputs.EventIdOutput;
import com.example.pass_in_v2.application.adapters.outputs.EventsOutput;
import com.example.pass_in_v2.infra.services.EventService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
  private final EventService service;

  @GetMapping
  public ResponseEntity<EventsOutput> listAll() {
    var response = this.service.listAll();
    return ResponseEntity.ok().body(response);
  }

  @GetMapping("/{eventId}/attendees")
  public ResponseEntity<Object> attendeesFromEvent(@PathVariable String eventId) {
    var attendeesFromEvent = this.service.attendeesFromEvent(eventId);
    return ResponseEntity.ok().body(attendeesFromEvent);
  }

  @PostMapping
  public ResponseEntity<EventIdOutput> createEvent(@RequestBody CreateEventInput input,
      UriComponentsBuilder uriComponentsBuilder) {
    var createdEvent = this.service.createEvent(input);
    var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(createdEvent.eventId()).toUri();

    return ResponseEntity.created(uri).body(createdEvent);
  }

  @PostMapping("/{eventId}/attendees")
  public ResponseEntity<AttendeeIdOutput> registerAttendeeOnEvent(@PathVariable String eventId,
      @RequestBody CreateAttendeeInput input) {
    var createdAttendee = this.service.registerAttendeeOnEvent(input, eventId);
    return ResponseEntity.ok().body(createdAttendee);
  }
}
