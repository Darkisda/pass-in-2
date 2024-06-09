package com.example.pass_in_v2.infra.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pass_in_v2.application.adapters.inputs.CreateAttendeeInput;
import com.example.pass_in_v2.application.adapters.inputs.CreateEventInput;
import com.example.pass_in_v2.application.adapters.outputs.AttendeeIdOutput;
import com.example.pass_in_v2.application.adapters.outputs.EventIdOutput;
import com.example.pass_in_v2.application.adapters.outputs.EventsOutput;
import com.example.pass_in_v2.application.usecases.AttendeesFromEvent;
import com.example.pass_in_v2.application.usecases.CreateEvent;
import com.example.pass_in_v2.application.usecases.ListEvents;
import com.example.pass_in_v2.application.usecases.RegisterAttendeeOnEvent;
import com.example.pass_in_v2.domain.attendee.AttendeeCheckInAggregate;
import com.example.pass_in_v2.infra.database.repositories.AttendeeJPARepository;
import com.example.pass_in_v2.infra.database.repositories.EventJPARepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EventService {
  private final EventJPARepository repository;
  private final AttendeeJPARepository attendeeRepository;

  public EventsOutput listAll() {
    var useCase = new ListEvents(this.repository);
    return useCase.exec();
  }

  public EventIdOutput createEvent(CreateEventInput input) {
    var useCase = new CreateEvent(this.repository);
    var response = useCase.exec(input);
    return response;
  }

  public AttendeeIdOutput registerAttendeeOnEvent(CreateAttendeeInput input, String eventId) {
    var useCase = new RegisterAttendeeOnEvent(this.attendeeRepository, this.repository);
    return useCase.exec(input, eventId);
  }

  public List<AttendeeCheckInAggregate> attendeesFromEvent(String eventId) {
    var useCase = new AttendeesFromEvent(attendeeRepository);
    return useCase.exec(eventId);
  }
}
