package com.utwente.ratefy.ScheduleService.services;

import com.utwente.ratefy.ScheduleService.models.Schedule;

import java.util.List;
import java.util.Optional;

public interface IScheduleService {

    List<Schedule> findAll();

    Optional<Schedule> findById(Integer id);

    Schedule save(Schedule schedule);

    void deleteById(Integer id);
}
