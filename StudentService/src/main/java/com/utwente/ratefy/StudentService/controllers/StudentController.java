package com.utwente.ratefy.StudentService.controllers;

import com.utwente.ratefy.StudentService.models.Feedback;
import com.utwente.ratefy.StudentService.models.Student;
import com.utwente.ratefy.StudentService.models.StudentDto;
import com.utwente.ratefy.StudentService.models.StudentMapper;
import com.utwente.ratefy.StudentService.services.IStudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    path = {"/v1/students"},
    produces = APPLICATION_JSON_VALUE)
public class StudentController {

  @Autowired private IStudentService studentService;

  @Autowired private StudentMapper studentMapper;

  @Autowired RestTemplate restTemplate;

  @GetMapping
  @Operation(summary = "Get all students")
  public ResponseEntity<List<StudentDto>> findAll() {
    return ResponseEntity.ok(studentMapper.toDTOs(studentService.findAll()));
  }

  @GetMapping(path = "/{id}")
  @Operation(summary = "Get a student by its id")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Found a student",
            content = {
              @Content(
                  mediaType = APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = Student.class))
            }),
        @ApiResponse(responseCode = "404", description = "Student not found", content = @Content)
      })
  public ResponseEntity<StudentDto> findById(@PathVariable(value = "id") Integer id) {
    Optional<Student> student = studentService.findById(id);
    return student
        .map(value -> ResponseEntity.ok().body(studentMapper.toDto(value)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Operation(summary = "Crate a new Student")
  @ApiResponse(
      responseCode = "201",
      description = "Student is created",
      content = {
        @Content(
            mediaType = APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = Student.class))
      })
  @PostMapping(consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity<StudentDto> createStudent(@Validated @Valid @RequestBody Student student) {
    final Student createdStudent = studentService.save(student);
    return ResponseEntity.status(HttpStatus.CREATED).body(studentMapper.toDto(createdStudent));
  }

  @Operation(summary = "Update student by its id")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Student was updated",
            content = {
              @Content(
                  mediaType = APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = Student.class))
            }),
        @ApiResponse(responseCode = "404", description = "Student not found", content = @Content)
      })
  @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE)
  ResponseEntity<StudentDto> updateStudent(
      @RequestBody Student incomingStudent, @PathVariable Integer id) {
    Optional<Student> optionalStudent = studentService.findById(id);
    if (optionalStudent.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.status(HttpStatus.OK)
        .body(studentMapper.toDto(studentService.update(incomingStudent, id)));
  }

  @DeleteMapping(path = "/{id}")
  @Operation(summary = "Delete a student by its id")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "204", description = "Student deleted", content = @Content),
        @ApiResponse(responseCode = "404", description = "Student not found", content = @Content),
      })
  public ResponseEntity deleteById(@PathVariable(value = "id") Integer id) {
    Optional<Student> optionalStudent = studentService.findById(id);
    if (optionalStudent.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    studentService.deleteById(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }

  @Operation(summary = "Crate a new Feedback")
  @ApiResponse(
      responseCode = "204",
      description = "Feedback is given",
      content = {
        @Content(
            mediaType = APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = Feedback.class)),
      })
  @PostMapping(path = "/feedback", consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity giveFeedback(@Validated @Valid @RequestBody Feedback feedback) {
    studentService.giveFeedback(feedback);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }
}
