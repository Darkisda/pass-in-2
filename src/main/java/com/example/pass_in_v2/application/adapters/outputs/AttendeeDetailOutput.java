package com.example.pass_in_v2.application.adapters.outputs;

import java.time.LocalDateTime;

public record AttendeeDetailOutput(String id, String name, String email, LocalDateTime createdAt,
    LocalDateTime checkedInAt) {}
