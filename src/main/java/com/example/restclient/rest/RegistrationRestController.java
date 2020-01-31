package com.example.restclient.rest;

import com.example.restclient.model.User;
import com.example.restclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration/users")
public class RegistrationRestController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return userService.add(user);
    }
}
