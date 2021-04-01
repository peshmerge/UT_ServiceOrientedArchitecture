package com.utwente.ratefy.ScheduleService.repositories;

import com.utwente.ratefy.ScheduleService.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}
