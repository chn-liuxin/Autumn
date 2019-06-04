package edu.cuit.autumn.mapper;

import edu.cuit.autumn.entity.Teacher;
import edu.cuit.autumn.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper {

    /**
     * 查找所有老师
     * @return List<User>
     */
    @Select("select * from teacher")
    List<Teacher> getAllTeacher();

    /**
     * 根据teacherName查找用户
     * @param teacherName
     * @return User
     */
    @Select("select * from teacher where teacher_name=#{teacherName}")
    List<Teacher> getTeacherByName(String teacherName);

    @Select("select * from teacher where teacher_id= #{teacherId}")
    Teacher getTeacherById(String teacherId);

    /**
     * 根据teacherId删除老师
     * @param teacherId
     */
    @Delete("delete from teacher where teacher_id=#{teacherId}")
    void deleteTeacher(String teacherId);

    /**
     * 增加老师
     */
    @Insert("insert into teacher values(#{teacherId},#{userId},#{teacherName},#{teacherSex},#{teacherBirthday},#{teacherPosition},#{teacherPhone})")
    void insertTeacher(Teacher teacher);

    /**
     * 修改老师信息
     * @param teacher
     */
    @Update("update teacher set teacher_name=#{teacherName},teacher_sex=#{teacherSex},teacher_birthday=#{teacherBirthday},teacher_position=#{teacherPosition},teacher_phone=#{teacherPhone} where teacher_id=#{teacherId}")
    void updateTeacher(Teacher teacher);

    /**
     * 修改老师信息
     * @param user
     */
    @Select("select * from teacher where user_id=#{userId}")
    Teacher getTeacherByUserId(User user);

    /**
     * 模糊查询老师
     */
    @Select("select * from teacher where teacher_name like #{teacherName}")
    List<Teacher> getTeacherFuzzy(String teacherName);

    /**
     * 获取老师总数
     */
    @Select("select count(*) from teacher")
    int getTeacherCount();

    /**
     * 根据索引获取老师列表
     */
    @Select("select * from teacher limit #{startIndex},#{endIndex}")
    List<Teacher> getTeacherByIndex(@Param("startIndex") int startIndex, @Param("endIndex") int endIndex);

}