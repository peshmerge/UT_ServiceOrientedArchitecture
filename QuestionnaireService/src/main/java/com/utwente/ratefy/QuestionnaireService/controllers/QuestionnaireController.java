package com.utwente.ratefy.QuestionnaireService.controllers;

import com.utwente.ratefy.QuestionnaireService.exceptions.RewardNotFoundException;
import com.utwente.ratefy.QuestionnaireService.models.Questionnaire;
import com.utwente.ratefy.QuestionnaireService.models.QuestionnaireDto;
import com.utwente.ratefy.QuestionnaireService.models.QuestionnaireMapper;
import com.utwente.ratefy.QuestionnaireService.services.IQuestionnaireService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin("*")
@RequestMapping(
    path = {"/v1/questionnaires"},
    produces = APPLICATION_JSON_VALUE)
public class QuestionnaireController {

  @Autowired private IQuestionnaireService questionnaireService;

  @Autowired private QuestionnaireMapper questionnaireMapper;

  @Autowired RestTemplate restTemplate;

  @Value("${reward-service-host}")
  String rewardService;

  @GetMapping
  @Operation(summary = "Get all questionnaires")
  public ResponseEntity<List<QuestionnaireDto>> findAll() {
    return ResponseEntity.ok(questionnaireMapper.toDTOs(questionnaireService.findAll()));
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
  public ResponseEntity<QuestionnaireDto> findById(@PathVariable(value = "id") Integer id) {
    Optional<Questionnaire> questionnaire = questionnaireService.findById(id);
    return questionnaire
        .map(value -> ResponseEntity.ok().body(questionnaireMapper.toDto(value)))
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
  public ResponseEntity<QuestionnaireDto> createQuestionnaire(
      @Validated @Valid @RequestBody Questionnaire questionnaire) throws ResponseStatusException {
    if (!rewardExists(questionnaire.getRewardId())) {
      throw new RewardNotFoundException(questionnaire.getRewardId());
    }
    final Questionnaire createdQuestionnaire = questionnaireService.save(questionnaire);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(questionnaireMapper.toDto(createdQuestionnaire));
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
                  schema = @Schema(implementation = QuestionnaireDto.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "Questionnaire not found",
            content = @Content)
      })
  @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE)
  ResponseEntity<QuestionnaireDto> updateQuestionnaire(
      @PathVariable Integer id, @RequestBody Questionnaire incomingQuestionnaire) {
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
    return ResponseEntity.status(HttpStatus.OK)
        .body(questionnaireMapper.toDto(updatedQuestionnaire));
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

  private boolean rewardExists(int rewardId) {
    try {
      ResponseEntity<Object> responseEntity =
          restTemplate.getForEntity(rewardService + rewardId, Object.class);
      return responseEntity.getStatusCode().is2xxSuccessful();
    } catch (HttpClientErrorException errorException) {
      return false;
    }
  }
}
