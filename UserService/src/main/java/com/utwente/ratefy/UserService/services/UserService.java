package com.utwente.ratefy.UserService.services;

import com.utwente.ratefy.UserService.models.Questionnaire;
import com.utwente.ratefy.UserService.models.User;
import com.utwente.ratefy.UserService.mq.QuestionnaireSender;
import com.utwente.ratefy.UserService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

  @Autowired private UserRepository userRepository;
  @Autowired private QuestionnaireSender questionnaireSender;
  @Autowired RestTemplate restTemplate;

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public Optional<User> findById(Integer id) {
    return userRepository.findById(id);
  }

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public void deleteById(Integer id) {
    userRepository.deleteById(id);
  }

  public void createQuestionnaire(Questionnaire questionnaire) {
    questionnaireSender.send(questionnaire);
  }
}
