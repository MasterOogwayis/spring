package com.test;

import com.zsw.io.ShouldClose;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ZhangShaowei on 2022/2/15 14:17
 */
@Slf4j
public class CloseableTests {


    public static void main(String[] args) {
        try (ShouldClose shouldClose = new ShouldClose()) {
            log.info("ShouldClose: {}", shouldClose);
        }
    }


}
