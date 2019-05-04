package edu.cuit.autumn.mapper;

import edu.cuit.autumn.entity.ReviewTheory;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewTheoryMapper {

    /**
     * 根据授课老师ID获取听课记录
     * @param teacherId
     * @return
     */
    @Select("select * from review_theory where teacher_id=#{teacherId}")
    List<ReviewTheory> getReviewTheoryByTeacherId(String teacherId);

    /**
     * 根据听课老师ID获取听课记录
     * @param reviewTeacherId
     * @return
     */
    @Select("select * from review_theory where review_teacher_id=#{teacherId}")
    List<ReviewTheory> getReviewTheoryByReviewTeacherId(String reviewTeacherId);

    /**
     * 根据ID获取听课记录
     * @param reviewId
     * @return
     */
    @Select("select * from review_theory where review_id=#{reviewId}")
    ReviewTheory getReviewTheoryById(String reviewId);

    /**
     * 增加一条听课记录
     */
    @Insert("insert into review_theory values(#{reviewId},#{teacherId},#{reviewTeacherId},#{classId},#{subjectId},#{lessonId},#{theme},#{reviewDate},#{reviewTime},#{score1},#{score2},#{score3},#{score4},#{score5},#{score6},#{score7},#{score8},#{score9},#{score10},#{totalScore},#{teacherSituation},#{atmosphere},#{studentSituation},#{bookSituation},#{headRate},#{sitSituation},#{teachType},#{overallEvaluation},#{recommend})")
    void insertReviewTheory(ReviewTheory reviewTheory);
}