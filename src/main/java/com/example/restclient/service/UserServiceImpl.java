package com.example.restclient.service;

import com.example.restclient.model.Role;
import com.example.restclient.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private PasswordEncoder passwordEncoder;
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getById(Long id) {
        return restTemplate.getForObject("http://localhost:8075/api/users/" + id, User.class);
    }

    @Override
    public List<User> getAll() {
        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity("http://localhost:8075/api/users", User[].class);
        List<User> users = null;
        if (!responseEntity.getStatusCode().isError()) {
            users = Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        }
        return users;
    }

    @Override
    public ResponseEntity<User> getUserByEmail(String email) {
        User user;
        try {
            user =  restTemplate.getForObject("http://localhost:8075/api/users/email/" + email, User.class);
        } catch (HttpClientErrorException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRoles().isEmpty()) {
            user.getRoles().add(new Role(1L, "USER"));
        }
        try {
            user = restTemplate.postForObject("http://localhost:8075/api/users", user, User.class);
        } catch (HttpClientErrorException exception) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @Override
    public void deleteById(Long id) {
        restTemplate.delete("http://localhost:8075/api/users/" + id);
    }

    @Override
    public void update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        restTemplate.put("http://localhost:8075/api/users/",user);
    }
}
