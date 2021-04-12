package com.utwente.ratefy.FeedbackService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

public class QuestionnaireNotFoundException extends ResponseStatusException {
    public QuestionnaireNotFoundException(Integer questionnaireId) {
        super(HttpStatus.NOT_FOUND, "Questionnaire with the id= " + questionnaireId + " not found!");
    }
}
