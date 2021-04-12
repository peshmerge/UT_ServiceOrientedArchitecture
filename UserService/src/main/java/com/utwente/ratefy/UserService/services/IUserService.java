package com.utwente.ratefy.UserService.services;

import com.utwente.ratefy.UserService.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> findAll();

    Optional<User> findById(Integer id);

    User save(User user);

    void deleteById(Integer id);
}
