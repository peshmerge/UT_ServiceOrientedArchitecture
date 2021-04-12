package com.utwente.ratefy.UserService;

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
	    path = {"/v1/users"},
	    produces = APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserRepository userRepository;
        
    @GetMapping
    public List<User> findAllUsers() {
    	return (List<User>) userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable(value = "id") Integer id) {
    	Optional<User> user = userRepository.findById(id);

        if(user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public User saveUser(@Validated @RequestBody User user) {
    	return userRepository.save(user);
    }
}