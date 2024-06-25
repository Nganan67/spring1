package com.example.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Override
    public String register(String username, String nickname, String password) {
        // 首先检查用户名是否已存在
        User existingUser = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if (existingUser != null) {
            // 如果存在，返回用户名已存在的信息
            return "注册失败，用户名已存在";
        }

        // 如果用户名不存在，执行注册逻辑
        User user = new User();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setPassword(password);

        // 插入用户信息到数据库
        int result = userMapper.insert(user);

        // 根据插入结果返回相应信息
        return result > 0 ? "注册成功" : "注册失败";
    }
    @Override
    public String login(String username, String password) {
        // 登录逻辑
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).eq("password", password);
        User user = userMapper.selectOne(queryWrapper);
        return (user != null) ? "登录成功" : "登录失败，用户名不存在或密码不正确";
    }

    @Override
    public String cancellation(String username) {
        //注销逻辑
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user != null) {
            userMapper.delete(queryWrapper);
            return "注销成功";
        }else {
            return "注销失败";
        }

    }
    public User findByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userMapper.selectOne(queryWrapper);
    }
}
