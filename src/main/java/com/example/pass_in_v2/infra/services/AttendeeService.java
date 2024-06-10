package com.example.pass_in_v2.infra.services;

import org.springframework.stereotype.Service;

import com.example.pass_in_v2.application.adapters.outputs.AttendeeDetailOutput;
import com.example.pass_in_v2.application.usecases.FindAttendeeById;
import com.example.pass_in_v2.application.usecases.RegisterCheckIn;
import com.example.pass_in_v2.infra.database.repositories.AttendeeJPARepository;
import com.example.pass_in_v2.infra.database.repositories.CheckInJPARepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AttendeeService {
  private final AttendeeJPARepository repository;
  private final CheckInJPARepository checkInRepository;

  public void registerCheckIn(String attendeeId) {
    var useCase = new RegisterCheckIn(this.checkInRepository, this.repository);
    useCase.exec(attendeeId);
  }

  public AttendeeDetailOutput findById(String attendeeId) {
    return new FindAttendeeById(this.repository).exec(attendeeId);
  }
}
