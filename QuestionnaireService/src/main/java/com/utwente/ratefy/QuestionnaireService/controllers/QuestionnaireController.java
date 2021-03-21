package com.utwente.ratefy.QuestionnaireService.controllers;

import com.utwente.ratefy.QuestionnaireService.models.Questionnaire;
import com.utwente.ratefy.QuestionnaireService.services.IQuestionnaireService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(
    path = {"/v1/questionnaires"},
    produces = APPLICATION_JSON_VALUE)
public class QuestionnaireController {

  @Autowired private IQuestionnaireService questionnaireService;

  @GetMapping
  @Operation(summary = "Get all questionnaires")
  public List<Questionnaire> findAll() {
    return questionnaireService.findAll();
  }

  @GetMapping(path = "/{id}")
  @Operation(summary = "Get a questionnaire by its id")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Found a questionnaire",
            content = {
              @Content(
                  mediaType = APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = Questionnaire.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "Questionnaire not found",
            content = @Content)
      })
  public ResponseEntity<Questionnaire> findById(@PathVariable(value = "id") Integer id) {
    Optional<Questionnaire> questionnaire = questionnaireService.findById(id);
    return questionnaire
        .map(value -> ResponseEntity.ok().body(value))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Operation(summary = "Crate a new Questionnaire")
  @ApiResponse(
      responseCode = "201",
      description = "Questionnaire is created",
      content = {
        @Content(
            mediaType = APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = Questionnaire.class))
      })
  @PostMapping(consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity<Questionnaire> createQuestionnaire(
      @Validated @RequestBody Questionnaire questionnaire) {
    final Questionnaire createdQuestionnaire = questionnaireService.save(questionnaire);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestionnaire);
  }

  @Operation(summary = "Update questionnaire by its id")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Questionnaire was updated",
            content = {
              @Content(
                  mediaType = APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = Questionnaire.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "Questionnaire not found",
            content = @Content)
      })
  @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE)
  ResponseEntity<Questionnaire> updateQuestionnaire(
      @RequestBody Questionnaire incomingQuestionnaire, @PathVariable Integer id) {
    Optional<Questionnaire> optionalQuestionnaire = questionnaireService.findById(id);
    if (optionalQuestionnaire.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    Questionnaire updatedQuestionnaire = optionalQuestionnaire.get();
    updatedQuestionnaire.setTitle(incomingQuestionnaire.getTitle());
    updatedQuestionnaire.setContent(incomingQuestionnaire.getContent());
    updatedQuestionnaire.setRewardId(incomingQuestionnaire.getRewardId());
    updatedQuestionnaire.setUpdatedAt(Instant.now());
    questionnaireService.save(updatedQuestionnaire);
    return ResponseEntity.status(HttpStatus.OK).body(updatedQuestionnaire);
  }

  @DeleteMapping(path = "/{id}")
  @Operation(summary = "Delete a questionnaire by its id")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "204",
            description = "Questionnaire deleted",
            content = @Content),
        @ApiResponse(
            responseCode = "404",
            description = "Questionnaire not found",
            content = @Content)
      })
  public ResponseEntity deleteById(@PathVariable(value = "id") Integer id) {
    Optional<Questionnaire> optionalQuestionnaire = questionnaireService.findById(id);
    if (optionalQuestionnaire.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    questionnaireService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }
}
