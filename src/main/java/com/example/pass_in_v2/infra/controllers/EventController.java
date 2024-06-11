package com.example.pass_in_v2.infra.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pass_in_v2.application.adapters.inputs.CreateAttendeeInput;
import com.example.pass_in_v2.application.adapters.inputs.CreateEventInput;
import com.example.pass_in_v2.application.adapters.outputs.AttendeeIdOutput;
import com.example.pass_in_v2.application.adapters.outputs.EventDetailOutput;
import com.example.pass_in_v2.application.adapters.outputs.EventIdOutput;
import com.example.pass_in_v2.domain.attendee.AttendeeCheckInAggregate;
import com.example.pass_in_v2.infra.services.EventService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
  private final EventService service;

  @GetMapping
  public ResponseEntity<List<EntityModel<EventDetailOutput>>> listAll() {
    var events = this.service.listAll();
    var response = events.stream().map(event -> EntityModel.of(event)
        .add(linkTo(methodOn(EventController.class).findById(event.id())).withSelfRel())
        .add(linkTo(methodOn(EventController.class).attendeesFromEvent(event.id())).withRel("attendees"))).toList();
    return ResponseEntity.ok().body(response);
  }

  @GetMapping("/{eventId}")
  public ResponseEntity<EntityModel<EventDetailOutput>> findById(@PathVariable String eventId) {
    var event = this.service.findById(eventId);
    var response = EntityModel.of(event);
    response.add(linkTo(methodOn(EventController.class).attendeesFromEvent(eventId)).withRel("attendees"));
    return ResponseEntity.ok().body(response);
  }

  @GetMapping("/{eventId}/attendees")
  public ResponseEntity<List<EntityModel<AttendeeCheckInAggregate>>> attendeesFromEvent(@PathVariable String eventId) {
    var attendeesFromEvent = this.service.attendeesFromEvent(eventId).stream().map(attendee -> EntityModel.of(attendee)
        .add(linkTo(methodOn(AttendeeController.class).findById(attendee.getId())).withSelfRel())).toList();
    return ResponseEntity.ok().body(attendeesFromEvent);
  }

  @PostMapping
  public ResponseEntity<EntityModel<EventIdOutput>> createEvent(@RequestBody CreateEventInput input) {
    var createdEvent = this.service.createEvent(input);
    var response = EntityModel.of(createdEvent)
        .add(linkTo(methodOn(EventController.class).findById(createdEvent.eventId())).withSelfRel());

    return ResponseEntity.status(201).body(response);
  }

  @PostMapping("/{eventId}/attendees")
  public ResponseEntity<EntityModel<AttendeeIdOutput>> registerAttendeeOnEvent(@PathVariable String eventId,
      @RequestBody CreateAttendeeInput input) {
    var createdAttendee = this.service.registerAttendeeOnEvent(input, eventId);
    var response = EntityModel.of(createdAttendee)
        .add(linkTo(methodOn(AttendeeController.class).findById(createdAttendee.attendeeId())).withSelfRel())
        .add(linkTo(methodOn(AttendeeController.class).registerCheckIn(createdAttendee.attendeeId()))
            .withRel("check-in"));
    return ResponseEntity.status(201).body(response);
  }
}
