package com.mathsena.todolistrocket.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.mathsena.todolistrocket.model.User;
import com.mathsena.todolistrocket.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired private UserRepository userRepository;

  @PostMapping("/create")
  public ResponseEntity<User> create(@RequestBody User user) {
    var userFound = this.userRepository.findByUsername(user.getUsername());

    if (userFound != null) {
      log.error("User already exists: {}", user.getUsername());
    }

    var hashedPassword = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
    user.setPassword(hashedPassword);

    var userCreated = this.userRepository.save(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
  }
}
