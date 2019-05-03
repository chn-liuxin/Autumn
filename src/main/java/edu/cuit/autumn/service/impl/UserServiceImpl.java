package edu.cuit.autumn.service.impl;

import edu.cuit.autumn.entity.User;
import edu.cuit.autumn.mapper.UserMapper;
import edu.cuit.autumn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    // 获取所有用户
    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    // 根据姓名获取用户
    @Override
    public User getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    // 根据ID获取用户
    @Override
    public User getUserById(String userId) {
        return userMapper.getUserById(userId);
    }

    // 根据identity获取用户列表
    @Override
    public List<User> getUserByIdentity(Integer userIdentity) {
        return userMapper.getUserByIdentity(userIdentity);
    }

    // 根据ID删除用户
    @Override
    public void deleteUser(String userId) {
        userMapper.deleteUser(userId);
    }

    // 更新用户信息
    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    // 新增用户
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

}
