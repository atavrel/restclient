package com.example.restclient.rest;

import com.example.restclient.model.Role;
import com.example.restclient.model.User;
import com.example.restclient.service.RoleService;
import com.example.restclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "/roles")
    public List<Role> getAllRoles() {
        return roleService.getAll();
    }

    @GetMapping(value = "/users")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping(value = "/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping(value = "/users/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return userService.add(user);
    }

    @PutMapping("/users")
    public void update(@RequestBody User user) {
        userService.update(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
