package com.utwente.ratefy.FeedbackService.controllers;

import com.utwente.ratefy.FeedbackService.exceptions.QuestionnaireNotFoundException;
import com.utwente.ratefy.FeedbackService.models.Feedback;
import com.utwente.ratefy.FeedbackService.models.FeedbackDto;
import com.utwente.ratefy.FeedbackService.models.FeedbackMapper;
import com.utwente.ratefy.FeedbackService.services.IFeedbackService;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping(
        path = {"/v1/feedbacks"},
        produces = APPLICATION_JSON_VALUE)
public class FeedbackController {

    @Autowired
    private IFeedbackService feedbackService;

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @GetMapping
    @Operation(summary = "Get all feedbacks")
    public ResponseEntity<List<FeedbackDto>> findAll() {
        return ResponseEntity.ok(feedbackMapper.toDTOs(feedbackService.findAll()));
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Get a feedback by its id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Found a feedback",
                            content = {
                                    @Content(
                                            mediaType = APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Feedback.class))
                            }),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Feedback not found",
                            content = @Content
                    )
            })
    public ResponseEntity<FeedbackDto> findById(@PathVariable(value = "id") Integer id) {
        Optional<Feedback> feedback = feedbackService.findById(id);
        return feedback
                .map(value -> ResponseEntity.ok().body(feedbackMapper.toDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crate a new Feedback")
    @ApiResponse(
            responseCode = "201",
            description = "Feedback is created",
            content = {
                    @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Feedback.class))
            })
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<FeedbackDto> createFeedback(
            @Validated @Valid @RequestBody Feedback feedback
    ) throws ResponseStatusException {
        if (!questionnaireExists(feedback.getQuestionnaireId())) {
            throw new QuestionnaireNotFoundException(feedback.getQuestionnaireId());
        }
        final Feedback createdFeedback = feedbackService.save(feedback);
        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackMapper.toDto(createdFeedback));
    }

    @Operation(summary = "Update feedback by its id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Feedback was updated",
                            content = {
                                    @Content(
                                            mediaType = APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Feedback.class))
                            }),
                    @ApiResponse(responseCode = "404", description = "Feedback not found", content = @Content)
            })
    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<FeedbackDto> updateFeedback(
            @RequestBody Feedback incomingFeedback, @PathVariable Integer id) {
        Optional<Feedback> optionalFeedback = feedbackService.findById(id);
        if (optionalFeedback.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Feedback updatedFeedback = optionalFeedback.get();
        updatedFeedback.setContent(incomingFeedback.getContent());
        updatedFeedback.setStudentId(incomingFeedback.getStudentId());
        updatedFeedback.setQuestionnaireId(incomingFeedback.getQuestionnaireId());
        updatedFeedback.setUpdatedAt(Instant.now());
        feedbackService.save(updatedFeedback);
        return ResponseEntity.status(HttpStatus.OK).body(feedbackMapper.toDto(updatedFeedback));
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete a feedback by its id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Feedback deleted", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Feedback not found", content = @Content)
            })
    public ResponseEntity deleteById(@PathVariable(value = "id") Integer id) {
        Optional<Feedback> optionalFeedback = feedbackService.findById(id);
        if (optionalFeedback.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        feedbackService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    private boolean questionnaireExists(int questionnaireId) {
        try {
            ResponseEntity<Object> responseEntity = restTemplate.getForEntity(
                    env.getProperty("questionnaire-service-name") + questionnaireId, Object.class);
            return responseEntity.getStatusCode().is2xxSuccessful();
        } catch (HttpClientErrorException errorException) {
            return false;
        }

    }
}
