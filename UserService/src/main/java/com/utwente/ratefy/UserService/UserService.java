package com.utwente.ratefy.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

  @Autowired private UserRepository userRepository;

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public Optional<User> findById(Integer id) {
    return userRepository.findById(id);
  }

  @Override
  public User save(User student) {
    return userRepository.save(student);
  }

  @Override
  public void delete(Integer id) {
	  userRepository.deleteById(id);
  }
}
