package com.example.controller;


import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    // 注册接口
    @PostMapping("/register")
    public String register(@RequestBody Map<String, String> userData) {
        String username = userData.get("username");
        String nickname = userData.get("nickname");
        String password = userData.get("password");
        return userService.register(username, nickname, password);
    }
    // 登录接口
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> userData) {
        String username = userData.get("username");
        String password = userData.get("password");
        return userService.login(username, password);
    }
    // 注销接口
    @DeleteMapping("/cancellation")
    public String cancellation(@RequestBody Map<String, String> userData) {
        String username = userData.get("username");
        return userService.cancellation(username);
    }
    //根据用户名查找信息
    @GetMapping("/findByUsername")
    public ResponseEntity<User> findUserByUsername(@RequestParam String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @RequestMapping("/hello")//用户测试连接
    public String Hello(){
        return "hello";
    }
}
