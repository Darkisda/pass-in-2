package com.example.pass_in_v2.application.adapters.outputs;

import java.time.LocalDateTime;
import java.util.Optional;

public record AttendeeCheckInOutput(String id, String name, String email, LocalDateTime createdAt,
    Optional<LocalDateTime> checkedInAt) {
}
