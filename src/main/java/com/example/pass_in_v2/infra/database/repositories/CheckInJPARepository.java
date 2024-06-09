package com.example.pass_in_v2.infra.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pass_in_v2.domain.checkin.CheckIn;
import com.example.pass_in_v2.domain.checkin.CheckInRepository;

public interface CheckInJPARepository extends JpaRepository<CheckIn, String>, CheckInRepository{
  
}
