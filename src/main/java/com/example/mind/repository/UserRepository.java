package com.example.mind.repository;

import com.example.mind.Entries.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 从User中实现JPA
public interface UserRepository extends JpaRepository<User, String> {

    // 通过用户名获取用户
    Optional<User> findByUsername(String username);

    User findByUserId(String userId);

}
