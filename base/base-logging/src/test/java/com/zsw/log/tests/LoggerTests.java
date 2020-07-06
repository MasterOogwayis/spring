package com.zsw.log.tests;

import com.zsw.log.support.LoggerBPR;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ZhangShaowei on 2020/6/18 10:31
 */
@Slf4j
public class LoggerTests {

    private static final LoggerBPR P4P = new LoggerBPR();

    public static void main(String[] args) {
        log.info(LoggerBPR.rule(P4P));
    }


}
