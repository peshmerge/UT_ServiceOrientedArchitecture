package com.utwente.ratefy.RewardService.controllers;

import com.utwente.ratefy.RewardService.models.Reward;
import com.utwente.ratefy.RewardService.models.RewardDto;
import com.utwente.ratefy.RewardService.models.RewardMapper;
import com.utwente.ratefy.RewardService.services.IRewardService;
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
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping(
        path = {"/v1/rewards"},
        produces = APPLICATION_JSON_VALUE)
public class RewardController {

    @Autowired
    private IRewardService rewardService;

    @Autowired
    private RewardMapper rewardMapper;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @GetMapping
    @Operation(summary = "Get all rewards")
    public ResponseEntity<List<RewardDto>> findAll() {
        return ResponseEntity.ok(rewardMapper.toDTOs(rewardService.findAll()));
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Get a reward by its id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Found a reward",
                            content = {
                                    @Content(
                                            mediaType = APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Reward.class))
                            }),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Reward not found",
                            content = @Content
                    )
            })
    public ResponseEntity<RewardDto> findById(@PathVariable(value = "id") Integer id) {
        Optional<Reward> reward = rewardService.findById(id);
        return reward
                .map(value -> ResponseEntity.ok().body(rewardMapper.toDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crate a new Reward")
    @ApiResponse(
            responseCode = "201",
            description = "Reward is created",
            content = {
                    @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Reward.class))
            })
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<RewardDto> createReward(@Validated @Valid @RequestBody Reward reward) {
        final Reward createdReward = rewardService.save(reward);
        return ResponseEntity.status(HttpStatus.CREATED).body(rewardMapper.toDto(createdReward));
    }

    @Operation(summary = "Update reward by its id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Reward was updated",
                            content = {
                                    @Content(
                                            mediaType = APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Reward.class))
                            }),
                    @ApiResponse(responseCode = "404", description = "Reward not found", content = @Content)
            })
    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<RewardDto> updateReward(
            @RequestBody Reward incomingReward, @PathVariable Integer id) {
        Optional<Reward> optionalReward = rewardService.findById(id);
        if (optionalReward.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Reward updatedReward = optionalReward.get();
        updatedReward.setTitle(incomingReward.getTitle());
        updatedReward.setValue(incomingReward.getValue());
        updatedReward.setUpdatedAt(Instant.now());
        rewardService.save(updatedReward);
        return ResponseEntity.status(HttpStatus.OK).body(rewardMapper.toDto(updatedReward));
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete a reward by its id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Reward deleted", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Reward not found", content = @Content)
            })
    public ResponseEntity deleteById(@PathVariable(value = "id") Integer id) {
        Optional<Reward> optionalReward = rewardService.findById(id);
        if (optionalReward.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        rewardService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
