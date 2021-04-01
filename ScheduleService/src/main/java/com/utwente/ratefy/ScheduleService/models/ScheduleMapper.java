package com.utwente.ratefy.ScheduleService.models;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);

    ScheduleDto toDto(Schedule schedule);

    List<ScheduleDto> toDTOs(List<Schedule> schedules);

    Schedule toEntity(ScheduleDto scheduleDto);
}