package com.example.pass_in_v2.infra.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pass_in_v2.application.adapters.outputs.AttendeeDetailOutput;
import com.example.pass_in_v2.infra.services.AttendeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/attendees")
@RequiredArgsConstructor
public class AttendeeController {
  private final AttendeeService service;

  @GetMapping("/{attendeeId}")
  public ResponseEntity<AttendeeDetailOutput> findById(@PathVariable String attendeeId) {
    return ResponseEntity.ok().body(this.service.findById(attendeeId));
  }

  @PostMapping("/{attendeeId}/check-in")
  public ResponseEntity<Object> registerCheckIn(@PathVariable String attendeeId) {
    this.service.registerCheckIn(attendeeId);

    return ResponseEntity.ok().build();
  }
}
