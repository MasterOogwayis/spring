package com.zsw.log.support;

import lombok.Getter;

/**
 * @author ZhangShaowei on 2020/6/18 10:28
 */
@Getter
public class LoggerBPR {

    private static final String LEFT = "<<--";
    private static final String RIGHT = "-->>";
    private static final String BEGIN = ":begin:";
    private static final String END = ":end:";
    private static final String COLON = ":";


    private String uuid;
    private String time;
    private Object target;


    public static String rule(LoggerBPR rule) {
        return LEFT + rule.getUuid() + COLON + "{}" + COLON + rule.getTime() + RIGHT;
    }

}
