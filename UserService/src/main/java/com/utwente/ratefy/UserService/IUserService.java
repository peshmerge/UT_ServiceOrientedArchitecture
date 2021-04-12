package com.utwente.ratefy.UserService;

import java.util.List;
import java.util.Optional;

public interface IUserService {

  List<User> findAll();

  Optional<User> findById(Integer id);

  User save(User questionnaire);

  void delete(Integer id);
}
