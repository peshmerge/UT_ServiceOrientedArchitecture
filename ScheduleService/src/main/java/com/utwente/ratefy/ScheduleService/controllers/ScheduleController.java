package com.utwente.ratefy.ScheduleService.controllers;

import com.utwente.ratefy.ScheduleService.models.Schedule;
import com.utwente.ratefy.ScheduleService.models.ScheduleDto;
import com.utwente.ratefy.ScheduleService.models.ScheduleMapper;
import com.utwente.ratefy.ScheduleService.services.IScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping(
        path = {"/v1/schedules"},
        produces = APPLICATION_JSON_VALUE)
public class ScheduleController {

    @Autowired
    private IScheduleService scheduleService;

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @GetMapping
    @Operation(summary = "Get all schedules")
    public ResponseEntity<List<ScheduleDto>> findAll() {
        return ResponseEntity.ok(scheduleMapper.toDTOs(scheduleService.findAll()));
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Get a schedule by its id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Found a schedule",
                            content = {
                                    @Content(
                                            mediaType = APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Schedule.class))
                            }),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Schedule not found",
                            content = @Content
                    )
            })
    public ResponseEntity<ScheduleDto> findById(@PathVariable(value = "id") Integer id) {
        Optional<Schedule> schedule = scheduleService.findById(id);
        System.out.println(schedule);
        return schedule
                .map(value -> ResponseEntity.ok().body(scheduleMapper.toDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crate a new Schedule")
    @ApiResponse(
            responseCode = "201",
            description = "Schedule is created",
            content = {
                    @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Schedule.class))
            })
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ScheduleDto> createSchedule(@Validated @Valid @RequestBody Schedule schedule) {
        final Schedule createdSchedule = scheduleService.save(schedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleMapper.toDto(createdSchedule));
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete a schedule by its id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Schedule deleted", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Schedule not found", content = @Content)
            })
    public ResponseEntity deleteById(@PathVariable(value = "id") Integer id) {
        Optional<Schedule> optionalSchedule = scheduleService.findById(id);
        if (optionalSchedule.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        scheduleService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
