package edu.cuit.autumn.mapper;

import edu.cuit.autumn.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
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
    User getUserById(Integer userId);

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
    void deleteUser(Integer userId);

}
