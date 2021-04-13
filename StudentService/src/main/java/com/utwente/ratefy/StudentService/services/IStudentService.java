package com.utwente.ratefy.StudentService.services;

import com.utwente.ratefy.StudentService.models.Feedback;
import com.utwente.ratefy.StudentService.models.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {

  List<Student> findAll();

  Optional<Student> findById(Integer id);

  Student save(Student student);

  Student update(Student incomingStudent, Integer Id);

  void deleteById(Integer id);

  void giveFeedback(Feedback feedback);
}
