package com.example.pass_in_v2.domain.event;

import java.util.List;
import java.util.Optional;

public interface EventRepository {
  Event save(Event event);
  List<Event> findAll();
  Optional<Event> findById(String id);
}
