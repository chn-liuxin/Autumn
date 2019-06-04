package edu.cuit.autumn.util;

import java.util.HashMap;
import java.util.Map;

public class ConvertLesson {

    private static Map<String, Integer> dayMap = null;
    private static Map<String, Integer> timeMap = null;

    static {
        dayMap = new HashMap<>();
        timeMap = new HashMap<>();
        dayMap.put("星期一", 1);
        dayMap.put("星期二", 2);
        dayMap.put("星期三", 3);
        dayMap.put("星期四", 4);
        dayMap.put("星期五", 5);
        dayMap.put("星期六", 6);
        dayMap.put("星期日", 7);
        timeMap.put("1-2节", 1);
        timeMap.put("3-4节", 2);
        timeMap.put("5-6节", 3);
        timeMap.put("7-8节", 4);
        timeMap.put("9-10节", 5);
        timeMap.put("11-12节",6);

    }

    public static int convertLessonTime(String lessonDay, String lessonTime) {

        return dayMap.get(lessonDay) * 10 + timeMap.get(lessonTime);
    }

}
