package edu.cuit.autumn.service;

import edu.cuit.autumn.entity.User;

import java.util.List;

public interface UserService {

    // 获取所有用户
    public List<User> getAllUser();

    // 根据ID获取用户
    public User getUserById(Integer userId);

    // 根据姓名获取用户
    public User getUserByName(String username);

    // 根据identity获取用户列表
    public List<User> getUserByIdentity(Integer userIdentity);

    // 根据ID删除用户
    public void deleteUser(Integer userId);

}
