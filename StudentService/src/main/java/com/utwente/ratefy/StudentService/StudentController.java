package com.utwente.ratefy.StudentService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(
	    path = {"/v1/students"},
	    produces = APPLICATION_JSON_VALUE)
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;
	        
	@GetMapping
	public List<Student> findAllUsers() {
		return (List<Student>) studentRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> findUserById(@PathVariable(value = "id") Integer id) {
		Optional<Student> student = studentRepository.findById(id);

		if(student.isPresent()) {
			return ResponseEntity.ok().body(student.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	    }

	    @PostMapping
	    public Student saveUser(@Validated @RequestBody Student student) {
	    	return studentRepository.save(student);
	    }
}
