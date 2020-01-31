package com.example.restclient.service;

import com.example.restclient.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    User getById(Long id);

    List<User> getAll();

    User getUserByEmail(String email);

    ResponseEntity<User> add(User user);

    void deleteById(Long id);

    void update(User user);
}