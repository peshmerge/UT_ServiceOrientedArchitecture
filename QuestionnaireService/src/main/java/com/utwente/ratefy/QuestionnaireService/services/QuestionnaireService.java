package com.utwente.ratefy.QuestionnaireService.services;

import com.utwente.ratefy.QuestionnaireService.models.Questionnaire;
import com.utwente.ratefy.QuestionnaireService.repositories.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionnaireService implements IQuestionnaireService {

  @Autowired private QuestionnaireRepository questionnaireRepository;

  @Override
  public List<Questionnaire> findAll() {
    return questionnaireRepository.findAll();
  }

  @Override
  public Optional<Questionnaire> findById(Integer id) {
    return questionnaireRepository.findById(id);
  }

  @Override
  public Questionnaire save(Questionnaire questionnaire) {
    return questionnaireRepository.save(questionnaire);
  }

  @Override
  public void delete(Integer id) {
    questionnaireRepository.deleteById(id);
  }
}
