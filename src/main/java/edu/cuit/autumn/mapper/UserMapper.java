package edu.cuit.autumn.mapper;

import edu.cuit.autumn.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    /**
     * 查找所有用户
     * @return List<User>
     */
    @Select("select * from user")
    List<User> getAllUser();

    /**
     * 根据userId查找用户
     * @param userId
     * @return User
     */
    @Select("select * from user where user_id=#{userId}")
    User getUserById(String userId);

    /**
     * 根据username查找用户
     * @param userName
     * @return User
     */
    @Select("select * from user where user_name=#{userName}")
    User getUserByName(String userName);

    /**
     * 根据用户类型查找用户
     * @param userIdentity
     * @return List<User>
     */
    @Select("select * from user where user_identity=#{userIdentity}")
    List<User> getUserByIdentity(Integer userIdentity);

    /**
     * 根据userId删除用户
     * @param userId
     */
    @Delete("delete from user where user_id=#{userId}")
    void deleteUser(String userId);

    /**
     * 更新用户信息
     */
    @Update("update user set user_name=#{userName},user_password=#{userPassword},user_identity=#{userIdentity} where user_id=#{userId}")
    void updateUser(User user);

    /**
     * 增加用户
     */
    @Insert("insert into user(user_id,user_identity,user_name,user_password) values(#{userId},#{userIdentity},#{userName},#{userPassword})")
    void insertUser(User user);

}
