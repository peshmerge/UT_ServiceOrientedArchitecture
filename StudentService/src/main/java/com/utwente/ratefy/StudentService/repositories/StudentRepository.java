package com.utwente.ratefy.StudentService.repositories;

import com.utwente.ratefy.StudentService.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
