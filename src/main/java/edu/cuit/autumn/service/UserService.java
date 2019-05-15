package edu.cuit.autumn.service;

import edu.cuit.autumn.entity.Teacher;
import edu.cuit.autumn.entity.User;

import java.util.List;

public interface UserService {

    // 获取所有用户
    public List<User> getAllUser();

    // 根据ID获取用户
    public User getUserById(String userId);

    // 根据姓名获取用户
    public User getUserByName(String username);

    // 根据identity获取用户列表
    public List<User> getUserByIdentity(Integer userIdentity);

    // 根据ID删除用户
    public void deleteUser(String userId);

    // 更新用户信息
    public void updateUser(User user);

    // 新增用户
    void insertUser(User user);

    Teacher getTeacherByUserId(User user);

}
