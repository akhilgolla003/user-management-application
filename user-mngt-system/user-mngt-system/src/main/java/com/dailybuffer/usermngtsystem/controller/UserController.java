package com.dailybuffer.usermngtsystem.controller;

import com.dailybuffer.usermngtsystem.model.User;
import com.dailybuffer.usermngtsystem.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user)
    {
        return userService.saveUser(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserBYId(@PathVariable("id") long id)
    {
        User user =  null;
        user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable("id") long id)
    {
        boolean deleted = false;
        deleted = userService.deleteUserById(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",deleted);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user)
    {
       user =  userService.updateUser(id,user);
       return ResponseEntity.ok(user);
    }
}
