package edu.cuit.autumn.mapper;

import edu.cuit.autumn.entity.Class;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassMapper {

    /**
     * 查找所有班级
     * @return List<Class>
     */
    @Select("select * from class")
    List<Class> getAllClass();

    /**
     * 根据专业名字查找班级
     * @param classMajor
     * @return Class
     */
    @Select("select * from class where class_major=#{classMajor}")
    List<Class> getClassByMajor(String classMajor);

    /**
     * 根据年级查找班级
     * @param classSession
     * @return Class
     */
    @Select("select * from class where class_session=#{classSession}")
    List<Class> getClassBySession(Short classSession);

    /**
     * 增加班级
     */
    @Insert("insert into class values(#{classId},#{classSession},#{classMajor},#{classNumber})")
    void insertClass(Class aClass);

    /**
     * 删除班级
     */
    @Delete("delete from class where class_id=#{classId}")
    void deleteClass(Class aClass);

    /**
     * 更新班级信息
     */
    @Update("update class set class_session=#{classSession},class_major=#{classMajor},class_number=#{classNumber} where class_id=#{classId}")
    void updateClass(Class aClass);

}