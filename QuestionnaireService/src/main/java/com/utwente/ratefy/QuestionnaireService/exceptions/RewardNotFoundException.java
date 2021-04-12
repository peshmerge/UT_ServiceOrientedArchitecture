package com.utwente.ratefy.QuestionnaireService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RewardNotFoundException extends ResponseStatusException {
    public RewardNotFoundException(Integer rewardId) {
        super(HttpStatus.NOT_FOUND, "Reward with the id= " + rewardId + " not found!");
    }
}
