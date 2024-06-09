package com.example.pass_in_v2.domain.checkin;
import java.util.Optional;

public interface CheckInRepository {
  CheckIn save(CheckIn checkIn);
  Optional<CheckIn> findByAttendeeId(String id);
}
