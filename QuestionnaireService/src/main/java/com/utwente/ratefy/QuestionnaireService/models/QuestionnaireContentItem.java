package com.utwente.ratefy.QuestionnaireService.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class QuestionnaireContentItem {

    @JsonProperty("question_id")
    @NotNull
    private Integer questionId;

    @JsonProperty(required = true)
    private String question;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionnaireContentItem(Integer questionId, String question) {
        this.questionId = questionId;
        this.question = question;
    }

    public QuestionnaireContentItem() {
    }
}
