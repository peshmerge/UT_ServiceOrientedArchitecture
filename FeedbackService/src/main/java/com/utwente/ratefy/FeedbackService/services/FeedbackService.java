package com.utwente.ratefy.FeedbackService.services;

import com.utwente.ratefy.FeedbackService.exceptions.QuestionnaireNotFoundException;
import com.utwente.ratefy.FeedbackService.models.Feedback;
import com.utwente.ratefy.FeedbackService.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService implements IFeedbackService {

  @Autowired private FeedbackRepository feedbackRepository;

  @Autowired RestTemplate restTemplate;

  @Value("${questionnaire-service-host}")
  String questionnaireService;

  @Override
  public List<Feedback> findAll() {
    return feedbackRepository.findAll();
  }

  @Override
  public Optional<Feedback> findById(Integer id) {
    return feedbackRepository.findById(id);
  }

  @Override
  public Feedback save(Feedback feedback) throws ResponseStatusException {
    if (!questionnaireExists(feedback.getQuestionnaireId())) {
      throw new QuestionnaireNotFoundException(feedback.getQuestionnaireId());
    }
    return feedbackRepository.save(feedback);
  }

  @Override
  public void deleteById(Integer id) {
    feedbackRepository.deleteById(id);
  }

  private boolean questionnaireExists(int questionnaireId) {
    try {
      ResponseEntity<Object> responseEntity =
          restTemplate.getForEntity(questionnaireService + questionnaireId, Object.class);
      return responseEntity.getStatusCode().is2xxSuccessful();
    } catch (HttpClientErrorException errorException) {
      return false;
    }
  }
}
