package com.utwente.ratefy.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {

  @Autowired private StudentRepository studentRepository;

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
  public void delete(Integer id) {
	  studentRepository.deleteById(id);
  }
}