package edu.cuit.autumn.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具
 */
public class RegularExpression {

    private static String goalStr = "init goalStr value";

    public static String match(String str) {
        Pattern p = Pattern.compile("ID:([A-Z]|[0-9])*");
        Matcher m = p.matcher(str);
        if (m.find()) {
            goalStr = m.group();
        }
        return goalStr.substring(3, 13);
    }
}
