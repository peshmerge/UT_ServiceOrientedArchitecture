package com.utwente.ratefy.StudentService.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utwente.ratefy.StudentService.models.Feedback;
import com.utwente.ratefy.StudentService.models.Student;
import com.utwente.ratefy.StudentService.mq.FeedbackSender;
import com.utwente.ratefy.StudentService.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {

  @Autowired private StudentRepository studentRepository;
  @Autowired private FeedbackSender feedbackSender;
  @Autowired RestTemplate restTemplate;

  @Value("${questionnaire-service-host}")
  String questionnaireService;

  @Value("${reward-service-host}")
  String rewardService;

  @Override
  public List<Student> findAll() {
    return studentRepository.findAll();
  }

  @Override
  public Optional<Student> findById(Integer id) {
    return studentRepository.findById(id);
  }

  @Override
  public Student save(Student student) {
    return studentRepository.save(student);
  }

  @Override
  public void deleteById(Integer id) {
    studentRepository.deleteById(id);
  }

  public void giveFeedback(Feedback feedback) {
    feedbackSender.send(feedback);
    // after giving feedback the user must gets points

    // Call the the questionnaire-service to get which reward belongs to the questionnaire
    Integer rewardId = getRewardIdUsingQuestionnaire(feedback.getQuestionnaireId());

    // Call reward service to get reward value
    Integer rewardValue = getRewardValueById(rewardId);

    // Update the student and assign the reward value
    Integer studentId = feedback.getStudentId();
    Optional<Student> optionalStudent = studentRepository.findById(studentId);
    Student student = optionalStudent.get();
    student.setPoints(student.getPoints() + rewardValue);
    this.update(student, studentId);
  }

  public Student update(Student incomingStudent, Integer id) {
    Optional<Student> optionalStudent = this.findById(id);
    Student updatedStudent = optionalStudent.get();
    updatedStudent.setName(incomingStudent.getName());
    updatedStudent.setStudentNumber(incomingStudent.getStudentNumber());
    updatedStudent.setEmail(incomingStudent.getEmail());
    updatedStudent.setOptIn(incomingStudent.getOptIn());
    updatedStudent.setPoints(incomingStudent.getPoints());
    updatedStudent.setUpdatedAt(Instant.now());
    return this.save(updatedStudent);
  }

  private Integer getRewardIdUsingQuestionnaire(int questionnaireId) {
    try {
      ResponseEntity<JsonNode> responseEntity =
          restTemplate.getForEntity(questionnaireService + questionnaireId, JsonNode.class);
      ObjectMapper mapper = new ObjectMapper();
      JsonNode jsonNode;
      jsonNode = responseEntity.getBody();
      return Integer.valueOf(jsonNode.findValue("reward_id").toString());
    } catch (HttpClientErrorException errorException) {
      return 0;
    }
  }

  private Integer getRewardValueById(int rewardId) {
    try {
      ResponseEntity<JsonNode> responseEntity =
          restTemplate.getForEntity(rewardService + rewardId, JsonNode.class);
      JsonNode jsonNode = responseEntity.getBody();
      return Integer.valueOf(jsonNode.findValue("value").toString());
    } catch (Exception errorException) {
      return 0;
    }
  }
}
