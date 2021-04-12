package com.utwente.ratefy.UserService.controllers;

import com.utwente.ratefy.UserService.exceptions.LastUserDeletionException;
import com.utwente.ratefy.UserService.models.User;
import com.utwente.ratefy.UserService.models.UserDto;
import com.utwente.ratefy.UserService.models.UserMapper;
import com.utwente.ratefy.UserService.services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    path = {"/v1/users"},
    produces = APPLICATION_JSON_VALUE)
public class UserController {

  @Autowired private IUserService userService;

  @Autowired private UserMapper userMapper;

  @Autowired RestTemplate restTemplate;


  @GetMapping
  @Operation(summary = "Get all users")
  public ResponseEntity<List<UserDto>> findAll() {
    return ResponseEntity.ok(userMapper.toDTOs(userService.findAll()));
  }

  @GetMapping(path = "/{id}")
  @Operation(summary = "Get a user by its id")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Found a user",
            content = {
              @Content(
                  mediaType = APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = User.class))
            }),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
      })
  public ResponseEntity<UserDto> findById(@PathVariable(value = "id") Integer id) {
    Optional<User> user = userService.findById(id);
    return user.map(value -> ResponseEntity.ok().body(userMapper.toDto(value)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Operation(summary = "Crate a new User")
  @ApiResponse(
      responseCode = "201",
      description = "User is created",
      content = {
        @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = User.class))
      })
  @PostMapping(consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDto> createUser(@Validated @Valid @RequestBody User user) {
    final User createdUser = userService.save(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDto(createdUser));
  }

  @Operation(summary = "Update user by its id")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "User was updated",
            content = {
              @Content(
                  mediaType = APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = User.class))
            }),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
      })
  @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE)
  ResponseEntity<UserDto> updateUser(@RequestBody User incomingUser, @PathVariable Integer id) {
    Optional<User> optionalUser = userService.findById(id);
    if (optionalUser.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    User updatedUser = optionalUser.get();
    updatedUser.setName(incomingUser.getName());
    updatedUser.setEmail(incomingUser.getEmail());
    updatedUser.setUpdatedAt(Instant.now());
    userService.save(updatedUser);
    return ResponseEntity.status(HttpStatus.OK).body(userMapper.toDto(updatedUser));
  }

  @DeleteMapping(path = "/{id}")
  @Operation(summary = "Delete a user by its id")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "204", description = "User deleted", content = @Content),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
        @ApiResponse(
            responseCode = "403",
            description = "User can't be deleted",
            content = @Content)
      })
  public ResponseEntity deleteById(@PathVariable(value = "id") Integer id) {
    Optional<User> optionalUser = userService.findById(id);
    if (optionalUser.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    if (userService.findAll().size() == 1) {
      throw new LastUserDeletionException(id);
    }
    userService.deleteById(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }
}
