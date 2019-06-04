package edu.cuit.autumn.mapper;

import edu.cuit.autumn.entity.ReviewExp;

import java.util.List;

import edu.cuit.autumn.entity.ReviewTheory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewExpMapper {
    /**
     * 根据授课老师ID获取听课记录
     * @param teacherId
     * @return
     */
    @Select("select * from review_exp where teacher_id=#{teacherId}")
    List<ReviewExp> getReviewExpByTeacherId(String teacherId);

    /**
     * 根据听课老师ID获取听课记录
     * @param reviewTeacherId
     * @return
     */
    @Select("select * from review_exp where review_teacher_id=#{teacherId}")
    List<ReviewExp> getReviewExpByReviewTeacherId(String reviewTeacherId);

    /**
     * 根据ID获取听课记录
     * @param reviewId
     * @return
     */
    @Select("select * from review_exp where review_id=#{reviewId}")
    ReviewExp getReviewExpById(String reviewId);

    /**
     * 增加一条听课记录
     */
    @Insert("insert into review_exp values(#{reviewId},#{teacherId},#{reviewTeacherId},#{classId},#{subjectId},#{classroom},#{theme},#{reviewDate},#{reviewTime},#{score1},#{score2},#{score3},#{score4},#{score5},#{score6},#{score7},#{score8},#{totalScore},#{comment},#{management},#{type})")
    void insertReviewTheory(ReviewExp reviewExp);
}