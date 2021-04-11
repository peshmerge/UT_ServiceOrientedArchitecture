package com.utwente.ratefy.ScheduleService.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-10T18:57:16+0200",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.3.jar, environment: Java 11.0.10 (Ubuntu)"
)
@Component
public class ScheduleMapperImpl implements ScheduleMapper {

    @Override
    public ScheduleDto toDto(Schedule schedule) {
        if ( schedule == null ) {
            return null;
        }

        ScheduleDto scheduleDto = new ScheduleDto();

        if ( schedule.getId() != null ) {
            scheduleDto.setId( schedule.getId() );
        }
        scheduleDto.setStudentId( schedule.getStudentId() );
        scheduleDto.setEventName( schedule.getEventName() );
        scheduleDto.setCourseName( schedule.getCourseName() );
        scheduleDto.setStartTime( schedule.getStartTime() );
        scheduleDto.setEndTime( schedule.getEndTime() );
        scheduleDto.setCreatedAt( schedule.getCreatedAt() );
        scheduleDto.setUpdatedAt( schedule.getUpdatedAt() );

        return scheduleDto;
    }

    @Override
    public List<ScheduleDto> toDTOs(List<Schedule> schedules) {
        if ( schedules == null ) {
            return null;
        }

        List<ScheduleDto> list = new ArrayList<ScheduleDto>( schedules.size() );
        for ( Schedule schedule : schedules ) {
            list.add( toDto( schedule ) );
        }

        return list;
    }

    @Override
    public Schedule toEntity(ScheduleDto scheduleDto) {
        if ( scheduleDto == null ) {
            return null;
        }

        Schedule schedule = new Schedule();

        schedule.setId( scheduleDto.getId() );
        schedule.setStudentId( scheduleDto.getStudentId() );
        schedule.setEventName( scheduleDto.getEventName() );
        schedule.setCourseName( scheduleDto.getCourseName() );
        schedule.setStartTime( scheduleDto.getStartTime() );
        schedule.setEndTime( scheduleDto.getEndTime() );
        schedule.setCreatedAt( scheduleDto.getCreatedAt() );
        schedule.setUpdatedAt( scheduleDto.getUpdatedAt() );

        return schedule;
    }
}
