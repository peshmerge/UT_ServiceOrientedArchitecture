package com.utwente.ratefy.UserService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LastUserDeletionException extends ResponseStatusException {
  public LastUserDeletionException(Integer userId) {
    super(
        HttpStatus.FORBIDDEN,
        "User with the id = " + userId + " is the last user, therefore can't be deleted!");
  }
}
