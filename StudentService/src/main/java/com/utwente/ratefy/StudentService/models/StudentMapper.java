package com.utwente.ratefy.StudentService.models;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDto toDto(Student student);

    List<StudentDto> toDTOs(List<Student> students);

    Student toEntity(StudentDto studentDto);
}