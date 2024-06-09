package com.example.pass_in_v2.infra.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pass_in_v2.domain.event.Event;
import com.example.pass_in_v2.domain.event.EventRepository;

public interface EventJPARepository extends JpaRepository<Event, String>, EventRepository{}
