package com.mathsena.todolistrocket.repository;

import com.mathsena.todolistrocket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
