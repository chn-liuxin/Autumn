package edu.cuit.autumn.util;

import edu.cuit.autumn.entity.ReviewExp;
import edu.cuit.autumn.entity.ReviewTheory;

public class ComputeAverageSorce {

    private static double averageScore = 0f;

    // 计算理论课评价表的加权平均分
    public static float computeScore(ReviewTheory reviewTheory) {
        averageScore = 0f;
        averageScore = averageScore + reviewTheory.getScore1() / (8.0 * 10);
        averageScore = averageScore + reviewTheory.getScore2() / (15.0 * 10);
        averageScore = averageScore + reviewTheory.getScore3() / (10.0 * 10);
        averageScore = averageScore + reviewTheory.getScore4() / (8.0 * 10);
        averageScore = averageScore + reviewTheory.getScore5() / (8.0 * 10);
        averageScore = averageScore + reviewTheory.getScore6() / (10.0 * 10);
        averageScore = averageScore + reviewTheory.getScore7() / (10.0 * 10);
        averageScore = averageScore + reviewTheory.getScore8() / (8.0 * 10);
        averageScore = averageScore + reviewTheory.getScore9() / (8.0 * 10);
        averageScore = averageScore + reviewTheory.getScore10() / (15.0 * 10);
        return (float) averageScore;
    }

    // 计算实践课评价表的加权平均分
    public static float computeScore(ReviewExp reviewExp) {
        averageScore = 0f;
        averageScore = averageScore + reviewExp.getScore1() / (15.0 * 8);
        averageScore = averageScore + reviewExp.getScore2() / (15.0 * 8);
        averageScore = averageScore + reviewExp.getScore3() / (10.0 * 8);
        averageScore = averageScore + reviewExp.getScore4() / (10.0 * 8);
        averageScore = averageScore + reviewExp.getScore5() / (10.0 * 8);
        averageScore = averageScore + reviewExp.getScore6() / (10.0 * 8);
        averageScore = averageScore + reviewExp.getScore7() / (10.0 * 8);
        averageScore = averageScore + reviewExp.getScore8() / (20.0 * 8);
        return (float) averageScore;
    }
}
