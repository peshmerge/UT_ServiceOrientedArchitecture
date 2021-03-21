package com.utwente.ratefy.QuestionnaireService.services;

import com.utwente.ratefy.QuestionnaireService.models.Questionnaire;

import java.util.List;
import java.util.Optional;

public interface IQuestionnaireService {

  List<Questionnaire> findAll();

  Optional<Questionnaire> findById(Integer id);

  Questionnaire save(Questionnaire questionnaire);

  void delete(Integer id);
}
