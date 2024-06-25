package com.example.service;


import com.example.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String register(String username, String nickname, String password);
    String login(String username, String password);
    String cancellation(String username);
    User findByUsername(String queryWrapper);
}
