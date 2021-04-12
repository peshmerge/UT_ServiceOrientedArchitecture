package com.utwente.ratefy.StudentService;

import java.util.List;
import java.util.Optional;

public interface IStudentService {

  List<Student> findAll();

  Optional<Student> findById(Integer id);

  Student save(Student questionnaire);

  void delete(Integer id);
}
